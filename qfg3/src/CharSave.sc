;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSAVE) ;52
(include game.sh) (include "52.shm")
(use Main)
(use Print)
(use LoadMany)
(use File)
(use Game)
(use User)
(use System)

(public
	CharSave 0
)

;; Bits in svMiscEquip
(define DISPEL_BIT		$0001)
(define TINDER_BIT		$0002)
(define PIN_BIT			$0004)
(define DAGGER_BIT		$0008)
(define WATERSKIN_BIT	$0010)
(define BIRD_BIT		$0020)
(define BABA_BIT		$0040)	;from QG1
(define ROPE_BIT		$0080)
(define TOOL_BIT		$0100)
(define SWORD_BIT		$0200)
(define SHIELD_BIT		$0400)


(define	EXTRA_DATA	18)	; Data items other than stats and name
(define	CHECK_DATA	10)	; Data items that are in check sums

(define textWidth 220)

(local
;; local data for saving hero stats for next game
	statsKey =  $53
	svCharType
	svHighRoyal
	svLowRoyal
	svMiscEquip
	[codedStats NUM_ATTRIBS]
	svRations
	svDaggers
	svOil
	svHealingPill
	svManaPill
	svPoisonCurePill
	bogus0 =  $19
	bogus1 =  $BE
	checkSum1
	checkSum2
	bogus2 =  $37
	bogus3 =  $6D
	bogus4 =  $C4
	bogus5 =  $F2
	checkSumKey =  $D0
	check1
	check2
	[YNSTR 5]
	[heroFileName 44]
	[bigStr 300]
	[bigStr2 300]
	hasSaved
	[str 40]
	answer
)

(enum				;states of saveHero Script
	congrats
	playItAgain
	;askSave
	getInfoFileName
	checkFile
	openFile
	writeHeroInfo
	writeComplete
	writeComplete2
	tryAgain
	saveDone
	;checkSong
	;redrawRoom
	goToCredits
)

(instance CharSave of Room
	(properties
		style IRISOUT
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID CHARSHEET) doit:)
		(keyDownHandler add: self)
		(User canControl: FALSE canInput: FALSE)
		(self setScript: saveHero)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(LoadMany FALSE FILE FILESEL)
		(super dispose:)
	)
)

(instance heroinfo of File
	(properties
		name "glory3.sav"
	)
)

(instance saveHero of Script
	
	(method (changeState newState &tmp whichSkill oldRoyal lineEndString oldExper retry)
		(switch (= state newState)
			(congrats
				(Animate)
				(Message MsgGet CHARSAVE N_CHARSAVE NULL C_FILENAME 1 @heroFileName)
				(if (>= score possibleScore)
					(messager say: N_CHARSAVE NULL C_CONGRATS_FULL_SCORE 1 self)
				else
					(Message MsgGet CHARSAVE N_CHARSAVE NULL C_CONGRATS 1 @bigStr2)
					(Format @bigStr @bigStr2 score possibleScore)
					(Print
						mode: teJustCenter
						width: textWidth
						addText: @bigStr
						init:
						self
					)
				)
				(= register TRUE)
			)
			(playItAgain
				(messager say: N_CHARSAVE NULL C_PLAY_IT_AGAIN 0 self)
				(= register TRUE)
			)
			(getInfoFileName
				(if
					(Print
						mode: teJustCenter
						addText: N_CHARSAVE NULL C_DISK_FILE 1 CHARSAVE
						addEdit: @heroFileName 30 0 30 @heroFileName
						init:
					)
					(heroinfo name: @heroFileName)
					(= cycles 2)
				else
					(self changeState: tryAgain)
				)
			)
			(checkFile
				(if (!= (heroinfo open: fRead) NULL)
					(Message MsgGet CHARSAVE N_CHARSAVE NULL C_OVERWRITE 1 @bigStr2)
					(Format @bigStr @bigStr2 (heroinfo name?))
					(switch
						(= answer
							(Print
								mode: teJustCenter
								addText: @bigStr
								addButton: 1 N_CHARSAVE NULL C_NO 1 10 45
								addButton: 2 N_CHARSAVE NULL C_YES 1 100 45
								init:
							)
						)
						(1
							(self changeState: getInfoFileName)
						)
						(2
							(heroinfo close:)
							(= cycles 1)
						)
					)
				else
					(= cycles 1)
				)
			)
			(openFile
				(if (heroinfo open: fTrunc)
					(heroinfo close:)
					(= seconds 2)
				else
					(Message MsgGet CHARSAVE N_CHARSAVE NULL C_WRITE_FAIL 1 @bigStr2)
					(Format @bigStr @bigStr2 (heroinfo name?))
					(Print mode: teJustCenter width: textWidth addText: @bigStr init:)
					(self changeState: tryAgain)
				)
			)
			(writeHeroInfo
				(if (not (heroinfo open: fAppend))
					(self changeState: tryAgain)
					(return)
				)
				(for ((= whichSkill 0)) (< whichSkill NUM_ATTRIBS) ((++ whichSkill))
					(= [codedStats whichSkill] [egoStats whichSkill])
				)
				
				;NOTE: Although the game saves these items, it's irrelevant since
				; the Hero starts the next game with nothing but his skills
				(= oldRoyal (+ ((inventory at: iRoyals) amount?) (/ commons 100)))
				(= svCharType heroType)
				(= svHighRoyal (/ oldRoyal 100))
				(= svLowRoyal (mod oldRoyal 100))
				(= svMiscEquip 0)
				(if (ego has: iDispell)
					(|= svMiscEquip DISPEL_BIT)
				)
				(if (ego has: iTinderbox)
					(|= svMiscEquip TINDER_BIT)
				)
				(if (ego has: iPin)
					(|= svMiscEquip PIN_BIT)
				)
				(if (ego has: iFineDagger)
					(|= svMiscEquip DAGGER_BIT)
				)
				(if (ego has: iWaterskin)
					(|= svMiscEquip WATERSKIN_BIT)
				)
				(if (ego has: iBird)
					(|= svMiscEquip BIRD_BIT)
				)
				(if (Btst fBabaFrog)
					(|= svMiscEquip BABA_BIT)
				)
				(if (ego has: iRope)
					(|= svMiscEquip ROPE_BIT)
				)
				(if (ego has: iToolkit)
					(|= svMiscEquip TOOL_BIT)
				)
				(if (ego has: iSword)
					(|= svMiscEquip SWORD_BIT)
				)
				(if (and (ego has: iShield) (== heroType FIGHTER))
					(|= svMiscEquip SHIELD_BIT)
				)
				(= svRations ((inventory at: iRations) amount?))
				(= svDaggers ((inventory at: iDaggers) amount?))
				(= svOil ((inventory at: iOil) amount?))
				(= svHealingPill ((inventory at: iHealPills) amount?))
				(= svManaPill ((inventory at: iManaPills) amount?))
				(= svPoisonCurePill ((inventory at: iCurePills) amount?))
				(= check1 checkSumKey)
				(for ((= whichSkill 0)) (< whichSkill (+ NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
					(= [statsKey (+ whichSkill 1)] [statsKey (+ whichSkill 1)])
					(+= check1 [statsKey (+ whichSkill 1)])
				)
				(= check2 0)
				(for ((= whichSkill 1)) (< whichSkill (+ NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
					(= [statsKey (+ whichSkill 1)] [statsKey (+ whichSkill 1)])
					(+= check2 [statsKey (+ whichSkill 1)])
				)
				(= checkSum1 check1)
				(= checkSum2 check2)
				(for ((= whichSkill 0)) (< whichSkill (+ NUM_ATTRIBS EXTRA_DATA)) ((++ whichSkill))
					(= [statsKey (+ whichSkill 1)] [statsKey (+ whichSkill 1)])
					(^= [statsKey (+ whichSkill 1)] [statsKey whichSkill])
				)
				(= lineEndString {*})
				(StrAt lineEndString 0 10)
				(heroinfo writeString: { glory3.sav_} lineEndString)
				(heroinfo writeString: @userName lineEndString)
				(= whichSkill 1)
				(for ((= whichSkill 1)) (< whichSkill (+ NUM_ATTRIBS EXTRA_DATA 1)) ((++ whichSkill))
					(Format @bigStr {%2x} (/ [statsKey whichSkill] 100))
					(heroinfo writeString: @bigStr)
					(Format @bigStr {%2x} (mod [statsKey whichSkill] 100))
					(heroinfo writeString: @bigStr)
				)
				(heroinfo writeString: lineEndString)
				(heroinfo close:)
				(= seconds 2)
			)
			(writeComplete
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(= register 1)
				(messager say: N_CHARSAVE NULL C_WRITE_COMPLETE 0 self)
			)
			(writeComplete2
				(= hasSaved TRUE)
				(self changeState: saveDone)
			)
			(tryAgain
				(if
					(= retry
						(Print
							mode: teJustCenter
							addText: N_CHARSAVE NULL C_TRY_AGAIN 1
							addButton: 1 N_CHARSAVE NULL C_NO 1 55 30
							addButton: 2 N_CHARSAVE NULL C_YES 1 95 30
							init:
						)
					)
					(switch retry
						(1
							(= retry saveDone)
						)
						(2
							(= retry getInfoFileName)
							(= bogus0 $19)
							(= bogus1 $BE)
							(= bogus2 $37)
							(= bogus3 $6D)
							(= bogus4 $C4)
							(= bogus5 $F2)
						)
					)
				)
				(self changeState: retry)
			)
			(saveDone
				(messager say: N_CHARSAVE NULL C_SAVE_DONE 0 self)
			)
			(goToCredits
				(curRoom newRoom: 890)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event message?) ENTER)
				register
			)
			(= register FALSE)
			(= cycles 1)
			(event claimed: TRUE)
		)
	)
)
