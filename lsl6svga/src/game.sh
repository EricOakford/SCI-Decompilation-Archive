;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)

;Event flags
(define fFragment 1)
(define fMissileDeath 2)
(define fMerrilyCartoon 8)
(define fRecordPlayerOn	9)
(define fShowingFeatures 22)
(define fEgoNaked 74)
(define fTouchedDaryl 91)
(define fThunderbirdCartoon 92)
(define fDarylStopsLarry 95)
(define fWonGame 97)
(define fShamaraAgrees 100)

;Inventory items
(enum
	iBadge
	iBatteries
	iBeaver
	iBeer
	iBelt
	iBracelet
	iBrochure
	iChampagne
	iCondom
	iCord
	iDiamond 
	iCollar
	iBastardFile
	iFilter
	iFlashlight
	iFloss
	iFlowers
	iGown
	iHandcuffs
	iHandcreme
	iTowerKey
	iRandomKey
	iRoomKey
	iLamp
	iLard
	iMatch
	iMinWater
	iOrange
	iOrchid
	iPearl
	iGlassCase
	iSunglasses
	iPolishCloth
	iSculpture
	iSoap
	iSwimsuit
	iToiletCover
	iToiletPaper
	iTowel
	iWashcloth
	iWordsOWisdom
	iWrench
)	

;Global scripts
(define COLORINIT	81)
(define LL6INV		85)
(define BAR_INV		86)
(define DEBUG		911)

;Talker scripts
(define tlkBiff			1144)
(define tlkShalo		1146)
(define tlkBillyDee		1230)
(define tlkWaitress		1242)
(define tlkLarry		1800)
(define tlkGammie		1801)
(define tlkCavaricchi	1802)
(define tlkThunderbird	1803)
(define tlkBurgundy		1804)
(define tlkRose			1805)
(define tlkMerrily		1806)
(define tlkGary			1807)
(define tlkKenny		1808)
(define tlkDaryl		1809)
(define tlkBartender	1810)
(define tlkChar			1811)
(define tlkShablee		1812)
(define tlkArt			1813)
(define tlkLarryThink	1814)
(define tlkMark			1815)
(define tlkShamara		1816)
(define tlkLarrySurprised	1817)
(define tlkLarryThumbsUp	1818)
(define tlkLarryBlush		1819)
(define tlkLarryWince		1820)
(define tlkTitle			1821)
(define tlkVoices			1822)
(define tlkAnnouncer		1823)
(define tlkLarryDoh			1825)
(define tlkInvNar			1826)