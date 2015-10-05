import os, sys, re

def readReport(filename):
    f = open(filename, 'r')
    report = ""
    heading = ""
    check = True
    for line in f:
        if check:
            newFilename, heading = createNewFileName(line)
            check = False
            continue
        if ord(line[0]) == 12:
            report = ' '.join([lines.strip() for lines in report.split('\n')])
            processReport(heading, report, newFilename)
            report = ""
            heading = ""
            check = True
        else:
            check = False
            report += line
    f.close()


def writeHTML(newFilename, report):
    htmlPath = <YOUR_NEW_HTML_PATH> + filename.split(".")[0]
    #print htmlPath
    if not os.path.exists(htmlPath):
        os.makedirs(htmlPath)
    newf = open(htmlPath+"\\"+newFilename, "w")
    print>>newf, report
    newf.close()


def processReport(heading, report, newFilename):
    terms = {"Accession Number:" : False, 
    "Encounter Number:" : False,
    "Attending Radiologist:" : False,
    "Resident Radiologist:" : False,
    "Order Date:" : False,
    "Ordered By:" : False,
    "Completion Date:" : False,
    "Electronically Signed" : False,
    "Findings:" : False,
    "Impression:" : False,
    "Description:" : False,
    "History:" : False,
    "Technique:" :False,
    "Images were reviewed and interpreted by": False,
    "Stony Brook University": False}
    htmlStart = "<html> <body> <p>"
    htmlEnd = "</p> </body> </html>"
    for key in terms.keys():
        if not terms[key]:
            m = re.search(key, report, re.I)
            #print m, key
            if m is not None:
                report = report[:m.start()] + ' <br> ' + report[m.start():]
    writeHTML(newFilename, heading + report)
    
def createNewFileName(line):
    personID = ""
    encounterID = ""
    datePattern = re.compile("^([0-9][0-9]-[A-Z][A-Z][A-Z]-[0-9][0-9][0-9][0-9])$")
    performedDate = ""
    timePattern = re.compile("^([0-9][0-9]:[0-9][0-9]:[0-9][0-9])$")
    performedTime = ""
    eventType = ""
    mrnNum = ""
    count = 0
    for words in line.strip().split(' '):
        if len(words) > 0:
            if count == 0:
                personID = words.strip()
            elif count == 1:
                encounterID = words.strip()
            elif datePattern.match(words.strip()):
                performedDate = words.strip()
            elif timePattern.match(words.strip()):
                performedTime = words.strip()
            else:
                eventType += words.strip() + " "
            count += 1
    mrnNum = eventType.split(' ')[len(eventType.split(' '))-2]
    eventType = eventType.replace(mrnNum, "")
    newFilename = "report_"+performedDate+"Time_"+performedTime.strip().replace(":","-")+"PersonID_"+personID+"EncounterID_"+encounterID+".html"
    heading = "<br><b>Performed on Date : " + performedDate + " " + performedTime + "</b>" + "<br><b>Event Title : " + eventType + "</b>" + "<br><b>Encounter ID : " + encounterID + "</b>" + "<br><b>Person ID : " + personID + "</b>" +"<br><b> MRN Number : " + mrnNum + "</b><br> <b> Report </b> <br>";
    #print "Created New File:", newFilename
    return newFilename, heading

filename = "mysbradsearch_1.txt"
readReport(filename)
#processReport(report)
