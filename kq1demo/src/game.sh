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

(define TEXT_X 214)
(define TEXT_Y 165)

;Script defines
(define SPEED		99)
(define KQ_WINDOW	801)
(define NEW_WALK	802)

;View defines
(define vEgo				0)
(define vEgoStand			2)
(define vEgoLookAround		3)
(define vEgoSmall			4)
(define vEgoStandSmall		7)
(define vEgoClimbSmall		8)
(define vEgoJumping			15)
(define vEgoDucking			16)
(define vEgoFall			18)
(define vEgoShrink			30)
(define vTinyEgo			31)
(define vEgoFlown			33)
(define vEgoBurned			52)
(define vEgoReflection		80)
(define vEgoReflectionStand	81)

(define vMoatMonster		107)
(define vMoatMonsterPeek	108)
(define vDragonHead			145)
(define vDragonSmoke		146)
(define vDragonBreath		148)
(define vCondor				160)

(define vLepKingSitting		170)
(define vLepKing			171)
(define vLepGirls			172)
(define vLepMan1			173)
(define vLepMan2			174)
(define vLepManWalk			175)
(define vLepGirl1Walk		176)
(define vLepGirl2Walk		177)
(define vLepHarpist			178)
(define vLepDrummer			179)

(define vCastleGuards		186)
(define vCastleGate			201)

(define vBucket				249)
(define vDragonCaveStuff	251)
(define vPoof				265)
(define vUrn				267)
(define vCaveStuff			274)
(define vGiant				440)
(define vGiantStompEgo		441)
(define vGiantGrabEgo		444)

(define vKQWindow			657)
(define vEgoTalkIcon		699)
(define vSpeed				777)
(define vTitle				913)
(define vSpark				950)

;Room and picture defines
(define rCastleGate			1)
(define rOutsideCave		22)
(define rDragonCave			51)
(define rGiant				58)
(define rBeanStalk1			70)
(define rBeanStalk2			71)
(define rBeanStalk3			72)
(define rCaveHole			73)
(define rLeprechaunThrone	77)
(define rSky				80)
(define pTitle				902)

;Demo room defines
(define dmBegin			1)
(define dmBird			22)
(define dmGiant			58)
(define dmLeps			73)
(define rOldRm1			501)
(define rOldRm2			502)
(define dmDragon		551)
(define pGiantAGI		558)
(define pBeanStalk3AGI	572)
(define pLeprechaunAGI	577)

;Demo view defines
(define vEgoAGI				500)
(define vDoorAGI			509)
(define vGlowAGI			511)
(define vGlowDoorAGI		512)
(define vDragonAGI			533)
(define vLep1AGI			547)
(define vLepKingAGI			554)
(define vGiantAGI			566)
(define vLep2AGI			578)
(define vLep3AGI			579)
(define vFlagsAGI			597)
(define vAlligator			607)