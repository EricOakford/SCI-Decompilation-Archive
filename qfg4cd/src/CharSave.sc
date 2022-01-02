;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSAVE) ;52
(include game.sh) (include "52.shm")
(use Main)
(use GloryRm)
(use DButton)
(use String)
(use Print)
(use LoadMany)
(use File)
(use User)
(use System)

(public
	CharSave 0
)


(define QG3_BIRD_BIT $0010)
(define QG4_BIRD_BIT $0020)

(define	EXTRA_DATA	18)	; Data items other than stats and name
(define	CHECK_DATA	10)	; Data items that are in check sums

(local
	statsKey =  $53
	svCharType
	svHighCrown
	svLowCrown
	svMiscEquip
	[codedStats NUM_ATTRIBS]
	;the following six locals were mistakenly decompiled
	; as part of codedStats, likely because they are unused
	svRations
	svDaggers
	svOil
	svHealingPotion
	svManaPotion
	svPoisonCurePotion

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
	heroFileName
	bigStr
	bigStr2
	hasSaved
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

(instance CharSave of GloryRm
	
	(method (init)
		(super init: &rest)
		(= heroFileName (String new:))
		(= bigStr (String new:))
		(= bigStr2 (String new:))
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
		name "glory4.sav"
	)
)

(instance saveHero of Script

	(method (changeState newState &tmp whichSkill oldCrown lineEndString oldExper retry)
		(switch (= state newState)
			(congrats
				(Message MsgGet CHARSAVE N_CHARSAVE NULL C_FILENAME 1 (heroFileName data?))
				(if (>= score possibleScore)
					(messager say: N_CHARSAVE NULL C_CONGRATS_FULL_SCORE 1 self)
				else
					(Message MsgGet CHARSAVE N_CHARSAVE NULL C_CONGRATS 1 (bigStr2 data?))
					(bigStr format: (bigStr2 data?) score possibleScore)
					(Print addText: bigStr init: self)
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
						addText: N_CHARSAVE NULL C_DISK_FILE 1 0 0 CHARSAVE
						font: 999
						addEdit: heroFileName 30 0 30 heroFileName
						init:
					)
					(heroinfo name: (heroFileName data?))
					(= cycles 2)
				else
					(self changeState: tryAgain)
				)
			)
			(checkFile
				(if (!= (heroinfo open: fRead) NULL)
					(Message MsgGet CHARSAVE N_CHARSAVE NULL C_OVERWRITE 1 (bigStr2 data?))
					(bigStr format: (bigStr2 data?) (heroinfo name?))
					(theGame setCursor: normalCursor)
					(switch
						(= answer
							(Print
								addText: bigStr
								classButton: MyButton
								font: 999
								addButton: 1 N_CHARSAVE NULL C_NO 1 25 45
								addButton: 2 N_CHARSAVE NULL C_YES 1 105 45
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
					(Message MsgGet CHARSAVE N_CHARSAVE NULL C_WRITE_FAIL 1 (bigStr2 data?))
					(bigStr format: (bigStr2 data?) (heroinfo name?))
					(Print addText: bigStr init:)
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
				(= oldCrown
					(+ ((inventory at: iPurse) amount?) (/ global154 100)) ;? shouldn't this use global 395 (for kopeks)?
				)
				(= svCharType heroType)
				(= svHighCrown (/ oldCrown 100))
				(= svLowCrown (mod oldCrown 100))
				(= svMiscEquip 0)
				(if (Btst fBoughtBlackBird)
					(|= svMiscEquip QG3_BIRD_BIT)
				)
				(if (ego has: iBlackbird)
					(|= svMiscEquip QG4_BIRD_BIT)
				)
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
				((= lineEndString (String newWith: 1 {*})) at: 0 10)
				(heroinfo writeString: { glory4.sav_})
				(heroinfo writeString: lineEndString)
				(heroinfo writeString: userName)
				(heroinfo writeString: lineEndString)
				(for ((= whichSkill 1)) (< whichSkill (+ NUM_ATTRIBS EXTRA_DATA 1)) ((++ whichSkill))
					(bigStr format: {%2x} (/ [statsKey whichSkill] 100))
					(heroinfo writeString: bigStr)
					(bigStr format: {%2x} (mod [statsKey whichSkill] 100))
					(heroinfo writeString: bigStr)
				)
				(heroinfo writeString: lineEndString)
				(heroinfo close:)
				(= seconds 2)
			)
			(writeComplete
				(= register 1)
				(messager say: N_CHARSAVE NULL C_WRITE_COMPLETE 0 self)
			)
			(writeComplete2
				(= hasSaved TRUE)
				(self changeState: saveDone)
			)
			(tryAgain
				(User canInput: TRUE)
				(if
					(= retry
						(Print
							addText: N_CHARSAVE NULL C_TRY_AGAIN 1
							font: 999
							addButton: 1 N_CHARSAVE NULL C_NO 1 55 30
							addButton: 2 N_CHARSAVE NULL C_YES 1 95 30
							init:
						)
					)
					(switch retry
						(1
							(User canInput: FALSE)
							(= retry saveDone)
						)
						(2
							(User canInput: FALSE)
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
				(curRoom newRoom: 160)
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

(class MyButton of DButton
	(properties
		view -546
		fore 0
		font 999
	)
)
