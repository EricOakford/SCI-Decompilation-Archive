;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSAVE)
(include game.sh) (include "601.shm")
(use Main)
(use Print)
(use File)
(use Game)
(use User)
(use Actor)
(use System)

(public
	CharSave 0
)

;; Bits in svMiscEquip
(define	SWORD_BIT	1)
(define	CHAIN_BIT	2)
(define	PICK_BIT		4)
(define	TOOL_BIT		8)
(define	MIRROR_BIT	16)
(define	BABA_BIT		32)			 
(define	SCORE_BIT	64)

(define	EXTRA_DATA	18)	; Data items other than stats and name
(define	CHECK_DATA	10)	; Data items that are in check sums

(local
;; local data for saving hero stats for next game
								 ;;;;;;;;;;;;;;;;;;start;;;;;;;;;;;;;;;;;;
	statsKey = $53			 ;;;;;;;;order dependent variables;;;;;;;;
	svCharType				 ;;;;;;;;order dependent variables;;;;;;;;
	svHighGold				 ;;;;;;;;order dependent variables;;;;;;;;
	svLowGold				 ;;;;;;;;order dependent variables;;;;;;;;
	svScore					 ;;;;;;;;order dependent variables;;;;;;;;
	svMiscEquip				 ;;;;;;;;order dependent variables;;;;;;;;
	[codedStats NUM_ATTRIBS] ;;;;;;;;order dependent variables;;;;;;;;
	svDaggers				 ;;;;;;;;order dependent variables;;;;;;;;
	svHealing				 ;;;;;;;;order dependent variables;;;;;;;;
	svMana					 ;;;;;;;;order dependent variables;;;;;;;;
	svStamina				 ;;;;;;;;order dependent variables;;;;;;;;
	svGhostOil				 ;;;;;;;;order dependent variables;;;;;;;;
	bogus0   = $79			 ;;;;;;;;order dependent variables;;;;;;;;
	bogus1   = $86			 ;;;;;;;;order dependent variables;;;;;;;;
	checkSum1				 ;;;;;;;;order dependent variables;;;;;;;;
	checkSum2				 ;;;;;;;;order dependent variables;;;;;;;;
	bogus2   = $43			 ;;;;;;;;order dependent variables;;;;;;;;
	bogus3   = $88			 ;;;;;;;;order dependent variables;;;;;;;;
	bogus4   = $ad			 ;;;;;;;;order dependent variables;;;;;;;;
	bogus5   = $f0			 ;;;;;;;;order dependent variables;;;;;;;;
	checkSumKey = $ce		 ;;;;;;;;order dependent variables;;;;;;;;
								 ;;;;;;;;;;;;;;;;;;;end;;;;;;;;;;;;;;;;;;;

	check1
	check2
	[YNSTR 5]
	[heroFileName 16]
	[bigStr 400]
	hasSaved					 ;TRUE if hero saved
	[str 40]
)

(enum				;states of saveHero Script
	askSave
	getInfoFileName
	getInfoFileName2
	openFile
	writeHeroInfo
	writeComplete
	tryAgain
;	badAnswer
	saveDone
	fadeToCredits
	credit1
	credit2
	credit3
	credit4
	credit5
	credit6
	credit7
	showTheControls
	quitTheGame
)


(instance CharSave of Room
	(properties
		noun N_CHARSAVE
		picture pBlueSkyForCarpet
		style IRISOUT
	)
	
	(method (init)
		(StatusLine enable:)
		(super init: &rest)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(self setScript: saveHero)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance heroInfo of File
	(properties
		name "glory1.sav"
	)
)

(instance saveHero of Script
	(properties)
	
	(method (changeState newState &tmp whichSkill oldGold temp2 printRet)
		(switch (= state newState)
			(askSave 0
				(Format @heroFileName 601 0)
				(if (>= score 500)
					(messager say: N_CHARSAVE NULL C_FINALSCORE 1 self)
				else
					(messager say: N_CHARSAVE NULL C_CONGRATULATIONS 1 self)
				)
			)
			(getInfoFileName 1
				(messager say: N_CHARSAVE NULL C_INSERTDISK 0 self)
			)
			(getInfoFileName2 2
				(if
					(Print
						mode: teJustCenter
						addText: {Disk file in which to save your Hero.}
						addEdit: @heroFileName 30 0 30 @heroFileName
						init:
					)
					(heroInfo name: @heroFileName)
					(= cycles 2)
				else
					(self changeState: tryAgain)
				)
			)
			(openFile 3
				(if (heroInfo open: fTrunc)
					(heroInfo close:)
					(= seconds 2)
				else
					(self changeState: tryAgain)
				)
			)
			(writeHeroInfo 4
				(if (not (heroInfo open: fAppend))
					(self changeState: tryAgain)
					(return)
				)
				(= whichSkill 0)
				(while (< whichSkill NUM_ATTRIBS)
					(= [codedStats whichSkill] [egoStats whichSkill])
					(++ whichSkill)
				)
				(= oldGold (/ ((inventory at: iSilver) amount?) 10))
				(= svCharType heroType)
				(= svHighGold (/ oldGold 100))
				(= svLowGold (mod oldGold 100))
				(= svScore score)
				(= svMiscEquip 0)
				(if (ego has: iSword) (|= svMiscEquip SWORD_BIT))
				(if (ego has: iChainmail) (|= svMiscEquip CHAIN_BIT))
				(if (ego has: iLockPick) (|= svMiscEquip PICK_BIT))
				(if (ego has: iThiefKit) (|= svMiscEquip TOOL_BIT))
				(if (ego has: iMagicMirror) (|= svMiscEquip MIRROR_BIT))
				(if (Btst fBabaFrog) (|= svMiscEquip BABA_BIT))
				(if (< 255 score) (|= svMiscEquip SCORE_BIT))
				(= svDaggers ((inventory at: iDagger) amount?))
				(= svHealing ((inventory at: iHealingPotion) amount?))
				(= svMana ((inventory at: iManaPotion) amount?))
				(= svStamina ((inventory at: iStaminaPotion) amount?))
				(= svGhostOil ((inventory at: iGhostOil) amount?))
				(= checkSum1 checkSumKey)
				(= whichSkill 0)
				(while (< whichSkill (+ NUM_ATTRIBS CHECK_DATA))
					(= [statsKey (+ whichSkill 1)]
						(& [statsKey (+ whichSkill 1)] 127)
					)
					(= checkSum1 (+ checkSum1 [statsKey (+ whichSkill 1)]))
					(= whichSkill (+ whichSkill 2))
				)
				(= checkSum2 0)
				(= whichSkill 1)
				(while (< whichSkill (+ NUM_ATTRIBS CHECK_DATA))
					(= [statsKey (+ whichSkill 1)]
						(& [statsKey (+ whichSkill 1)] 127)
					)
					(= checkSum2 (+ checkSum2 [statsKey (+ whichSkill 1)]))
					(= whichSkill (+ whichSkill 2))
				)
				(&= checkSum1 127)
				(&= checkSum2 127)
				(= whichSkill 0)
				(while (< whichSkill (+ NUM_ATTRIBS EXTRA_DATA))
					(= [statsKey (+ whichSkill 1)]
						(& [statsKey (+ whichSkill 1)] 127)
					)
					(= [statsKey (+ whichSkill 1)]
						(^ [statsKey (+ whichSkill 1)] [statsKey whichSkill])
					)
					(++ whichSkill)
				)
				(heroInfo writeString: @userName)
				(heroInfo writeString: {\n})
				(= whichSkill 1)
				(while (< whichSkill (+ NUM_ATTRIBS EXTRA_DATA 1))
					(Format @bigStr 601 1 [statsKey whichSkill])
					(heroInfo writeString: @bigStr)
					(++ whichSkill)
				)
				(heroInfo writeString: {\n})
				(heroInfo close:)
				(= seconds 2)
			)
			(writeComplete 5
				(messager say: N_CHARSAVE NULL C_WRITECOMPLETE)
				(= hasSaved TRUE)
				(= cycles 2)
			)
			(tryAgain 6
				(switch
					(= printRet
						(Print
							mode: teJustCenter
							addText: {Do you wish to try saving your character again?}
							addButton: 0 { No_} 55 30
							addButton: 1 { Yes_} 95 30
							init:
						)
					)
					(0
						(messager say: N_CHARSAVE 0 (= state saveDone) 1 self)
					)
					(1
						(= printRet 2)
						(= bogus0 $79)
						(= bogus1 $86)
						(= bogus2 $43)	
						(= bogus3 $88)	
						(= bogus4 $ad)	
						(= bogus5 $f0)	
						(self changeState: printRet)
					)
				)
			)
			(saveDone
				(messager say: N_CHARSAVE NULL C_SAVEDONE 1 self)
			)
			(fadeToCredits
				(curRoom drawPic: 903 FADEOUT)
				(= seconds 3)
			)
			(credit1
				(User canControl: TRUE canInput: TRUE)
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(animators init:)
				(= seconds 8)
			)
			(credit2
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(animators dispose:)
				(artists init:)
				(= seconds 8)
			)
			(credit3
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(artists dispose:)
				(programmers init:)
				(= seconds 8)
			)
			(credit4
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(programmers dispose:)
				(musicDirector init:)
				(= seconds 8)
			)
			(credit5
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(musicDirector dispose:)
				(developmentSys init:)
				(= seconds 8)
			)
			(credit6
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(developmentSys dispose:)
				(soundEffects init:)
				(= seconds 8)
			)
			(credit7
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(soundEffects dispose:)
				(qualityDudes init:)
				(= seconds 8)
			)
			(showTheControls
				(gameControls window: (ScriptID 0 10) show:)
				(= ticks 10)
			)
			(quitTheGame
				(= quit TRUE)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(>= state credit1)
				(or
					(== (event type?) keyDown)
					(== (event type?) mouseDown)
					(== (event message?) ENTER)
				)
			)
			(event claimed: TRUE)
			(gameControls window: (ScriptID 0 10) show:)
		)
	)
)

(instance endStatus of Code
	
	(method (doit strg)
		(Format strg 601 2 score)
	)
)

(instance animators of View
	(properties
		x 170
		y 167
		view 902
		loop 1
	)
)

(instance artists of View
	(properties
		x 161
		y 135
		view 902
		loop 2
	)
)

(instance programmers of View
	(properties
		x 158
		y 110
		view 902
		loop 3
	)
)

(instance musicDirector of View
	(properties
		x 105
		y 65
		view 902
		loop 4
	)
)

(instance developmentSys of View
	(properties
		x 85
		y 165
		view 902
		loop 5
	)
)

(instance soundEffects of View
	(properties
		x 95
		y 65
		view 902
		loop 6
	)
)

(instance qualityDudes of View
	(properties
		x 94
		y 65
		view 902
		loop 7
	)
)
