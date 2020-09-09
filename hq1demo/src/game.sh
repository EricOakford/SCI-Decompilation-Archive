;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

(enum 1	;character classes
	FIGHTER
	MAGIC_USER
	THIEF
)

;Script defines
(define INTRO	1)
(define ROLES	2)
(define TOWN	3)
(define MAGIC	4)
(define DART	5)
(define GHOSTS	6)
(define BABA	7)
(define CAVE	8)
(define MEADOW	9)
(define FOREST	10)
(define ARENA	11)
(define LAST	12)
(define SPEED	99)
(define DEBUG	800)

;View defines
(define vEgo				0)
(define vEgoStanding		4)
(define vEgoRunning			5)
(define vEgoFleeing			7)
(define vBabaSkulls			23)
(define vGraveyardStuff		63)
(define vZara				315)
(define vDartBoard			341)
(define vBear				420)
(define vSaurus				430)
(define vCheetaur			440)
(define vMoreCheetaur		442)
(define vDragon				460)
(define vMoreDragon			462)
(define vSelEgo				505)
(define vCharSel			506)
(define vEgoBattleArms		540)
(define vEgoBattleBody		541)
(define vOtto				606)
(define vSpeed				799)
(define vIcons				800)
(define vStatusBar			803)
(define vHalfFlame			907)
(define vSierraPresents 	908)
(define vTitleSpell			913)
(define vSubtitle			914)
(define vHeroText			918)
(define vHero2Text			919)

;Picture and room defines
(define rEranasPeace	10)
(define rBearCave		14)
(define rKoboldCave		15)
(define rBabaHutOutside	22)
(define rGraveyard		64)
(define rBigEgo			148)
(define rTownOutlook	300)
(define rMagicShop		314)
(define rDagNabIt		340)
(define pBlack 			400)
(define rGenericForest	700)
(define pCharSel		905)
(define pHalfDome		906)

;Sound defines
(define sThemeSong		1)
(define sHardBattle		2)
(define sGhosts			12)
(define sCave			20)
(define sBabaHutOutside	23)
(define sEranasPeace	24)
(define sForest			25)
(define sTeleport		28)
(define sKnifeStick1	29)
(define sKnifeStick2	30)
(define sKnifeThrow		31)
(define sHardBattleEnd	38)
(define sThunder		45)
(define sWind			47)
(define sMagicShop		67)
(define sBrigand		73)
(define sTown			93)
(define sEndGame		99)
;PC speaker sounds (1 voice)
(define sHardBattleIBM		102)
(define sEranasPeaceIBM		124)
(define sTeleportIBM		128)
(define sKnifeStick1IBM		129)
(define sKnifeStick2IBM		130)
(define sKnifeThrowIBM		131)
(define sHardBattleEndIBM	138)
(define sThunderIBM			145)
(define sBrigandIBM			173)
(define sEndGameIBM			199)
(define sThemeIBM			201)
;Tandy sounds (4 voices)
(define sThemeTandy		301)