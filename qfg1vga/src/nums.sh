;;; Sierra Script 1.0 - (do not remove this comment)
;Hero's Quest Framework scripts
(define MAIN			0)
(define HQINIT			1)
(define NOTICE			2)
(define INVDESC			3)	;text only
(define DRINK 			5)
(define EAT 			6)
(define SLEEP 			7)
(define REST			8)
(define NOTICE2			9)

(define SLEEPALLNIGHT	88)		;EO: This has number 88, which was previously a room removed from this version.
(define CASTDART		100)
(define THROWKNIFE		101)
(define THROWROCK		102)
(define GETROCK			103)
(define CASTCALM		104)
(define CASTOPEN		105)
(define CASTDAZZ		106)
(define EGOSEZ			120)	;doVerb on ego
(define PRAGFAIL		121)	;pragmaFail on ego
(define PAINREACTION	155)
(define INTRO			200)
(define CHARSEL			202)
(define CHALLOC			203)
(define CHARSHEET		204)
(define STATUSBAR		205)
(define GLORYINV		206)
(define MAZEBUG			238)
(define SPEED			299)
(define TELLER			550)
(define WARE			551)
(define ABOUT			556)
(define ACTIONBAR		557)
(define SPELLS			558)
(define GLORY_WINDOW	559)
(define ENDGAME			600)
(define CHARSAVE 		601)
(define ENDGAME2		602)
(define LOGOROOM		603)
(define DOOR 			800)
(define TALKOBJ			803)
(define KEYCURSOR		810)
(define	TARGET			812)
(define TIME			813)
(define PROCS			814)
(define GLORY_PRINT		815)
(define GLORY_OBSTACLES 816)
(define STARTAROOM		896)

; Locales and Regions
(define DEBUG	 298) ;Locale
(define TOWN 	 801) ;Locale
(define FOREST 	 804) ;Locale
(define GHOSTS 	 805) ;Actors and Scripts for CEMETERY
(define CEMETERY 806) ;Region
(define CASTLE 	 807) ;Locale
(define STREET 	 811) ;Locale

;All Fighting Characters
(define vBear 			420)
(define vBearDefeated 	421)
(define vBearFight 		422)
(define vBearHumanFight 423)
(define vArenaDazzle 424)
(define vMinotaur 		425)
(define vMinotaurDefeated 426)
(define vMinotaurFight 	427)
(define vSaurus 		430)
(define vSaurusDefeated 431)
(define vSaurusFight 	432)
(define vMantray 		435)
(define vMantrayDefeated 436)
(define vMantrayFight 	437)
(define vCheetaur 		440)
(define vCheetaurDefeated 	441)
(define vCheetaurFight	442)
(define vGoblin 		445)
(define vGoblinDefeated 446)
(define vGoblinFight	447)
(define vGoblinBush 	448)
(define vTroll 			450)
(define vTrollDefeated	451)
(define vTrollFight 	452)
(define vOgre 			455)
(define vOgreDefeated 	456)
(define vOgreFight 		457)
(define vDragon			460)
(define vDragonDefeated 	461)
(define vDragonFight 	462)
(define vBrigand 		465)
(define vBrigandDefeated 466)
(define vBrigandFight 	467)
(define vBrigandLeader		470)	;unused, but was likely the leader's arena
(define vBrigandLeaderFight 473)

(define pBlueSkyForCarpet 750)

(define vIcons		800)
(define vCharSheet	802)
(define vStatusBar	803)
(define vMonoStatusBar 804)	;unused in VGA

(define vCreditsDragon 901)
(define vCredits 902)
(define vHalfDragon 906)
(define vHalfFlame 907)
(define vSierraPresents 908)
(define vTitleSpell 913)
(define vQFGshadow 918)
(define vQFG 919)
(define vQFG2 920)
(define vTrialByFire 925)
(define vEndCreditsCarpet 925)

(define vControlIcons	995)