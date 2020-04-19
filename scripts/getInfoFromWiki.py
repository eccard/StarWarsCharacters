import requests

characterPages = [
    "https://en.wikipedia.org/wiki/Supreme_Leader_Snoke",
    "https://en.wikipedia.org/wiki/Maz_Kanata",
    "https://en.wikipedia.org/wiki/Finn_(Star_Wars)",
    "https://en.wikipedia.org/wiki/Palpatine",
    "https://en.wikipedia.org/wiki/General_Grievous",
    "https://en.wikipedia.org/wiki/Count_Dooku",
    "https://en.wikipedia.org/wiki/Jango_Fett",
    "https://en.wikipedia.org/wiki/Jabba_the_Hutt",
    "https://en.wikipedia.org/wiki/Boba_Fett",
    "https://en.wikipedia.org/wiki/Lando_Calrissian",
    "https://en.wikipedia.org/wiki/Poe_Dameron",
    "https://en.wikipedia.org/wiki/Rose_Tico",
    "https://pt.wikipedia.org/wiki/Vice_Almirante_Holdo",
    "https://en.wikipedia.org/wiki/Qui-Gon_Jinn",
    "https://en.wikipedia.org/wiki/Mace_Windu",
    "https://en.wikipedia.org/wiki/Padm%C3%A9_Amidala",
    "https://en.wikipedia.org/wiki/Chewbacca",
    "https://en.wikipedia.org/wiki/Han_Solo",
    "https://en.wikipedia.org/wiki/BB-8",
    "https://en.wikipedia.org/wiki/C-3PO",
    "https://en.wikipedia.org/wiki/R2-D2",
    "https://en.wikipedia.org/wiki/Yoda",
    "https://en.wikipedia.org/wiki/Princess_Leia",
    "https://en.wikipedia.org/wiki/Luke_Skywalker",
    "https://en.wikipedia.org/wiki/Obi-Wan_Kenobi",
    "https://en.wikipedia.org/wiki/Kylo_Ren"
    "https://en.wikipedia.org/wiki/Darth_Vader"

]


def findPartOfContent(content, whatToFindSimp, sToFind):
    index = content.find(sToFind)
    if (index != -1):
        secIdx = content.find("</td>", index)
        if (secIdx != -1):
            subs = content[index:secIdx]
            print("\t" + whatToFindSimp + " " + subs)
        else:
            print("can't find \"</td>\"")
    else:
        print("can't fid " + whatToFindSimp)


def getFullName(content):
    findPartOfContent(content, "Full name=", "<th scope=\"row\">Full name</th>")


def getLastAppearence(content):
    findPartOfContent(content, "Last appearence=", "<th scope=\"row\">Last appearance</th>")


def getOpccupation(content):
    findPartOfContent(content, "Occupation=", "<th scope=\"row\">Occupation</th>")


def getGender(content):
    findPartOfContent(content, "Gender=", "<th scope=\"row\">Gender</th>")


def getFirstAppearance(content):
    findPartOfContent(content, "First Appearance=", "<th scope=\"row\">First appearance</th>")



def getInfos(page):
    r = requests.get(page)
    getFullName(r.content)
    getFirstAppearance(r.content)
    getGender(r.content)
    getLastAppearence(r.content)


for page in characterPages:
    print(page)
    getInfos(page)
    print("\n")
