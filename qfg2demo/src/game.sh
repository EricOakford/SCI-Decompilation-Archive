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

;Demo script defines
(define INTRO	1)
(define INN		2)
(define ALLEY	4)
(define MONEY	5)
(define HAREM	6)
(define DESERT	7)
(define SCORP	8)
(define ROPE	10)
(define RITUAL	11)
(define IBLIS	12)
(define DANCE	75)
(define FLAME	77)
(define FULLLOOP	78)
(define SPEED	99)
(define DEBUG	800)

;Dance step types
(enum
	DanceView
	DanceCel
	DanceEndLoop
	DanceBegLoop
	DanceMove
	DanceRelPosn
	DancePosn
	DanceSpecial
	DanceEnd
)

;Views
(define vEgo					0)
(define vEgoStanding			4)
(define vEgoRidingSaurus		12)
(define vEgoCastLightning		40)
(define vSpeed					98)
(define vShameen				104)
(define vMusician				109)
(define vDanceMusician			111)
(define vDance2					113)
(define vMoneychanger			125)
(define vEgoTightrope			285)
(define vMoneychangerGuard		355)
(define vServant				432)
(define vHaremGirl				435)
(define vEgoPeek				438)
(define vAdAvisRope				495)
(define vAdAvisRitual			505)
(define vThark					510)
(define vScorpion				670)
(define vEgoSwordFight			673)
(define vEgoCaughtByScorpion	675)
(define vAlley					700)
(define vAlleyDoor				701)
(define vHalfGenie				755)
(define vHalfSmoke				756)
(define vCarpet					758)
(define vIblisParts				791)
(define vLightningStrikes		792)
(define vIcons					999)

;Picture and room defines
(define pBlack				10)
(define rKattaInn			100)
(define rKattaClose			110)
(define rShemaDance			112)
(define rMoneychanger		120)
(define rDestructCity		340)
(define rKhaveenHouse		410)
(define rHaremGirlHouse		430)
(define rTightrope			490)
(define rRitualChamber		500)
(define rGenDesert			660)
(define rCombatIsolated		681)
(define rAlley				700)
(define rHalfDome			750)
(define rTitleScreen		760)
(define rDistantCity		780)
(define rIblisDestroys		790)

;Sound defines
(define sExplosion		12)
(define sHardBattle		54)
(define sHardBattleEnd	55)
(define sGenieHand1		751)
(define sGenieHand2		752)
(define sGenieClaps		753)
(define sGenieLaughs	754)
(define sTitleFlame		761)
(define sCaravan		790)