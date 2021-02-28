;;; Sierra Script 1.0 - (do not remove this comment)
(script# IMPORT) ;54
(include game.sh) (include "54.shm")
(use Main)
(use GloryWindow)
(use Intrface)
(use Print)
(use Dialog)
(use IconBar)
(use FileSel)
(use LoadMany)
(use GControl)
(use File)
(use Game)
(use System)

(public
	import 0
)

(local
;; local data for restoring hero stats from previous game
	statsKey =  $53
	svCharType
	svExperience
	svHighDinar
	svLowDinar
	svMiscEquip
	[codedStats QG2_NUM_ATTRIBS]
	svDagger
	svHealingPill
	svManaPill
	svStaminaPill
	svPoisonCurePill
	bogus0 =  $A0
	bogus1 =  $3E
	checkSum1
	checkSum2
	bogus2 =  $2F
	bogus3 =  $90
	bogus4 =  $19
	bogus5 =  $A3
	checkSumKey =  $DA
	check1
	check2
	validFile
	[heroFileName 44]
	[bigStr 300]
	[str 40]
	[butBuf1 20]
	[butBuf2 20]
	[butBuf3 60]
	statMap = [
		STR		INT		AGIL		VIT		LUCK			WEAPON	PARRY
		DODGE		STEALTH	PICK		THROW		CLIMB			MAGIC		COMM
		HONOR		EXPER		HEALTH	STAMINA	MANA			OPEN		DETMAGIC
		TRIGGER	DAZZLE	ZAP		CALM		FLAMEDART	FETCH		FORCEBOLT
		LEVITATE	REVERSAL]
	
;; local data for restoring hero stats from QG1	
	statsKeyQG1 =  $53
	svCharTypeQG1
	svHighGold
	svLowGold
	svScore
	svMiscEquipQG1
	[codedStatsQG1 QG1_NUM_ATTRIBS]
	svDaggerQG1
	svHealingPotion
	svManaPotion
	svStaminaPotion
	svGhostOil
	bogus6 =  $79
	bogus7 =  $86
	checkSum3
	checkSum4
	bogus8 =  $43
	bogus9 =  $88
	bogus10 =  $AD
	bogus11 =  $F0
	checkSumKeyQG1 =  $CE
	statMapQG1 = [
		STR		INT		AGIL		VIT		LUCK			WEAPON	PARRY
		DODGE		STEALTH	PICK		THROW		CLIMB			MAGIC		COMM
		HONOR		EXPER		HEALTH	STAMINA	MANA			OPEN		DETMAGIC
		TRIGGER	DAZZLE	ZAP		CALM		FLAMEDART	FETCH]
	[curDir 20]
	newHeroType
	[butBuf4 5]
	[butBuf5 5]
	[butBuf6 10]
	[butBuf7 10]
	[butBuf8 10]
	[butBuf9 10]
)

(enum				;states of importHero Script
	waitABit
	askRestore
	waitABit2
	getInfoFileName
	;getInfoFileName2
	restoreFile
	readComplete
	importDone
	tryAgain
)

;; Bits in svMiscEquip (QG2)
(define	FINESWORD_BIT 	$0001) ; fine sword
(define	FLAMESWORD_BIT	$0002) ; flaming sword
(define	COMPASS_BIT		$0003) ; compass
(define	PIN_BIT			$0004) ; spahire pin
(define	LAMP_BIT			$0008) ; brass lamp
(define	TOKEN_BIT		$0010) ; EOF token
(define	GLASSES_BIT		$0020) ; X-Ray Glasses

; things transferred from "Quest for Glory 1"
(define	BABA_BIT			$0030) ; Flag set from QG1
(define	SWORD_BIT		$0040) ; Sword
(define	CHAIN_BIT		$0080) ;
(define	PICK_BIT			$0100) ;
(define	TOOL_BIT			$0200) ;

;; Bits in svMiscEquip (QG1)
(define	QG1_SWORD_BIT		$0001)
(define	QG1_CHAIN_BIT		$0002)
(define	QG1_PICK_BIT		$0004)
(define	QG1_TOOL_BIT		$0008)
(define	QG1_MIRROR_BIT		$0010)
(define QG1_BABA_BIT		$0020)		 
(define	QG1_SCORE_BIT		$0040)


(define	EXTRA_DATA	18)	; Data items other than stats and name
(define	CHECK_DATA	10)	; Data items that are in check sums

(procedure (MakeZero &tmp i)
	(for ((= i 1)) (< i COMM) ((++ i))
		(= [oldStats i] 0)
	)
	(= heroType 0)
	(for ((= i 0)) (< i iLastInvItem) ((++ i))
		((inventory at: i) amount: 0 owner: 0)
	)
	(= score 0)
	(StrCpy @userName {xxxxxxxxxxy})
	(for ((= i 0)) (< i (+ QG2_NUM_ATTRIBS EXTRA_DATA)) ((++ i))
		(= [statsKey (+ i 1)] 0)
	)
)

(procedure (RestoreHero)
	(if (not (heroinfo open: fRead))
		(Message MsgGet IMPORT N_ROOM V_DOIT C_CANT_FIND 1 @bigStr)
		(Printf @bigStr (heroinfo name?))
		(return FALSE)
	)
	(heroinfo readString: @userName 52)
	(heroinfo readString: @bigStr 90)
	(cond 
		((== (StrLen @bigStr) 86)
			(if (not (RestoreFromQG1))
				(return FALSE)
			)
		)
		((not (RestoreFromQG2))
			(return FALSE)
		)
	)
	(return TRUE)
)

(procedure (ConvWord ascii)
	(return
		(+
			(ConvByte (>> ascii 8))
			(* (ConvByte (& ascii 255)) 16)
		)
	)
)

(procedure (ConvByte ascii)
	(return
		(cond 
			((== ascii 32)
				(return 0)
			)
			((and (<= 48 ascii) (<= ascii 57))
				(return (- ascii 48))
			)
			(else
				(return (- ascii 87))
			)
		)
	)
)

(procedure (MakeFileName name path root &tmp sep pathLen)
	(if (= pathLen (StrLen path))
		(= sep (StrAt path (- pathLen 1)))
		(Format name {%s%c%s} path
			(if (OneOf sep 92 58) 0 else 92)
			root
		)
	else
		(StrCpy name root)
	)
	(return name)
)

(procedure (ChangeDir obj)
	(if (GetCurSaveDir @curDir)
		(theGame setCursor: waitCursor)
		(if (not (savedHeros init:))
			(return FALSE)
		)
		(obj dispose:)
		(obj init:)
		(savedHeros draw:)
	)
	(theGame setCursor: normalCursor)
	(return TRUE)
)

(procedure (GetCurSaveDir dir &tmp result [newDir 60])
	(= result 0)
	(repeat
		(if
			(not
				(= result
					(Print
						addText: N_GET_FILE_NAME NULL C_NEW_DIRECTORY 1 0 12
						addEdit: (StrCpy @newDir dir) 30 0 26 dir
						addButton: 1 N_GET_FILE_NAME NULL NULL 1 0 40
						addButton: 0 N_GET_FILE_NAME NULL NULL 2 0 54
						init:
					)
				)
			)
			(break)
		)
		(if (not (StrLen @newDir))
			(GetCWD @newDir)
		)
		(if (ValidPath @newDir)
			(StrCpy @curDir @newDir)
			(break)
		)
		(Message MsgGet IMPORT N_ROOM V_DOIT C_INVALID_DIR 1 @bigStr)
		(Printf @bigStr @newDir)
	)
	(return result)
)

(procedure (RestoreFromQG2 &tmp whichSkill i)
	(for ((= whichSkill 0)) (< whichSkill (+ QG2_NUM_ATTRIBS EXTRA_DATA)) ((++ whichSkill))
		(= [statsKey (+ whichSkill 1)]
			(ConvWord [bigStr whichSkill])
		)
	)
	(for ((= whichSkill (+ QG2_NUM_ATTRIBS EXTRA_DATA))) (< 0 whichSkill) ((-- whichSkill))
		(^= [statsKey whichSkill] (& [statsKey (- whichSkill 1)] 255))
	)
	(= check1 checkSumKey)
	(for ((= whichSkill 0)) (< whichSkill (+ QG2_NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
		(&= [statsKey (+ whichSkill 1)] 255)
		(+= check1 [statsKey (+ whichSkill 1)])
	)
	(= check2 0)
	(for ((= whichSkill 1)) (< whichSkill (+ QG2_NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
		(&= [statsKey (+ whichSkill 1)] 255)
		(+= check2 [statsKey (+ whichSkill 1)])
	)
	(&= check1 255)
	(&= check2 255)
	(if
		(or
			(!= check1 checkSum1)
			(!= check2 checkSum2)
		)
		(Prints N_ROOM V_DOIT C_INVALID_FILE_QG2 1)
		(return FALSE)
	)
	(for ((= whichSkill 0)) (< whichSkill QG2_NUM_ATTRIBS) ((++ whichSkill))
		(= [egoStats [statMap whichSkill]] [codedStats whichSkill])
		(if
			(not
				(if (< COMM [statMap whichSkill]) (< [statMap whichSkill] OPEN))
			)
			(if
				(not
					(if (<= 0 [codedStats whichSkill])
						(<= [codedStats whichSkill] 300)
					)
				)
				(Prints N_ROOM V_DOIT C_INVALID_FILE_QG2 1)
				(return FALSE)
			)
		)
	)
	;try to make sure correct character type is selected
	(if (== svCharType FIGHTER)
		(if (and [egoStats ZAP] [egoStats REVERSAL])
			(= svCharType MAGIC_USER)
		else
			(= i 0)
			(for ((= whichSkill 0)) (< whichSkill COMM) ((++ whichSkill)) 
				(if [egoStats whichSkill] (++ i))
			)
			(if (or (not [egoStats PARRY]) (== i COMM))
				(= svCharType THIEF)
			)
		)
	)
	(= heroType
		(= origHeroType svCharType)
	)
	(if (and (not (& svMiscEquip CHAIN_BIT)) (== heroType PALADIN))
		(Bset fWasWizard)
	)
	(if (== heroType PALADIN) (= origHeroType FIGHTER))
	(if ((inventory at: iDaggers) amount: svDagger)
		((inventory at: iDaggers) owner: ego)
		((inventory at: iDaggers) realOwner: ego)
	)
	(if ((inventory at: iHealPills) amount: svHealingPill)
		((inventory at: iHealPills) owner: ego)
		((inventory at: iHealPills) realOwner: ego)
	)
	(if ((inventory at: iManaPills) amount: svManaPill)
		((inventory at: iManaPills) owner: ego)
		((inventory at: iManaPills) realOwner: ego)
	)
	(if ((inventory at: iCurePills) amount: svPoisonCurePill)
		((inventory at: iCurePills) owner: ego)
		((inventory at: iCurePills) realOwner: ego)
	)
	(if (&= svMiscEquip BABA_BIT)
		(Bset fBabaFrog)
	)
	(if (& svMiscEquip PIN_BIT)
		(ego get: iPin 1)
	)
	(return TRUE)
)

(procedure (RestoreFromQG1 &tmp whichSkill)
	(for ((= whichSkill 0)) (< whichSkill (+ QG1_NUM_ATTRIBS EXTRA_DATA)) ((++ whichSkill))
		(= [statsKeyQG1 (+ whichSkill 1)]
			(ConvWord [bigStr whichSkill])
		)
	)
	(for ((= whichSkill (+ QG1_NUM_ATTRIBS EXTRA_DATA))) (< 0 whichSkill) ((-- whichSkill))
		(^= [statsKeyQG1 whichSkill] (& [statsKeyQG1 (- whichSkill 1)] 127))
	)
	(= check1 checkSumKeyQG1)
	(= whichSkill 0)
	(for ((= whichSkill 0)) (< whichSkill (+ QG1_NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
		(&= [statsKeyQG1 (+ whichSkill 1)] 127)
		(+= check1 [statsKeyQG1 (+ whichSkill 1)])
	)
	(= check2 0)
	(for ((= whichSkill 1)) (< whichSkill (+ QG1_NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
		(&= [statsKeyQG1 (+ whichSkill 1)] 127)
		(+= check2 [statsKeyQG1 (+ whichSkill 1)])
	)
	(&= check1 127)
	(&= check2 127)
	(if (or (!= check1 checkSum3) (!= check2 checkSum4))
		(Prints N_ROOM V_DOIT C_INVALID_FILE_QG1 1)
		(return FALSE)
	)
	(for ((= whichSkill 0)) (< whichSkill QG1_NUM_ATTRIBS) ((++ whichSkill))
		(= [egoStats [statMapQG1 whichSkill]] (* 2 [codedStatsQG1 whichSkill]))
		(if
			(not
				(if (< MAGIC [statMapQG1 whichSkill]) (< [statMapQG1 whichSkill] OPEN))
			)
			(if
				(not
					(if (<= 0 [codedStatsQG1 whichSkill]) (<= [codedStatsQG1 whichSkill] 300))
				)
				(Prints N_ROOM V_DOIT C_INVALID_FILE_QG1 1)
				(return FALSE)
			)
		)
	)
	(= heroType
		(= origHeroType svCharTypeQG1)
	)
	(= [egoStats COMM]
		(/ (+ (* [egoStats INT] 2) [egoStats LUCK]) 3)
	)
	(= [egoStats HONOR] 100)
	(if ((inventory at: iDaggers) amount: svDaggerQG1)
		((inventory at: iDaggers) owner: ego)
	)
	(if ((inventory at: iRoyals) amount: 100)
		((inventory at: iRoyals) owner: ego)
	)
	(if (& svMiscEquipQG1 QG1_BABA_BIT)
		(Bset fBabaFrog)
	)
	(return TRUE)
)


(procedure (WizardBecomesPaladin)
	(Message MsgGet IMPORT N_ROOM V_DOIT C_PALADIN 1 @butBuf4)
	(Message MsgGet IMPORT N_ROOM V_DOIT C_WIZARD 1 @butBuf5)
	(quest init: show: dispose:)
	(return newHeroType)
)

(procedure (ChangeType)
	(Message MsgGet IMPORT N_ROOM V_DOIT C_FIGHTER 1 @butBuf6)
	(Message MsgGet IMPORT N_ROOM V_DOIT C_WIZARD 1 @butBuf7)
	(Message MsgGet IMPORT N_ROOM V_DOIT C_THIEF 1 @butBuf8)
	(Message MsgGet IMPORT N_ROOM V_DOIT C_PALADIN 1 @butBuf9)
	(choose init: show: dispose:)
	(return newHeroType)
)

(instance import of Room
	(properties
		picture 130
	)
	
	(method (init)
		(HandsOff)
		(theIconBar disable:)
		(super init: &rest)
		(Palette PALIntensity 72 255 60)
		(self setScript: importHero)
	)
	
	(method (dispose)
		(LoadMany FALSE FILE FILESEL)
		(super dispose:)
	)
)

(instance heroinfo of File
	(properties
		name "glory2.sav"
	)
)

(instance importHero of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(waitABit
				(= cycles 3)
			)
			(askRestore
				(Message MsgGet IMPORT N_ROOM V_DOIT C_FILENAME 1 @heroFileName)
				(messager say: N_ROOM NULL NULL 0 self)
			)
			(waitABit2
				(= cycles 2)
			)
			(getInfoFileName
				(if (getFileName doit: @heroFileName)
					(heroinfo name: @heroFileName)
					(= cycles 2)
				else
					(self changeState: 6)
				)
			)
			(restoreFile
				(MakeZero)
				(if (= validFile (RestoreHero))
					(= cycles 2)
				else
					(self changeState: tryAgain)
				)
			)
			(readComplete
				(messager say: N_ROOM V_DOIT C_IMPORT_SUCCESS 0 self)
			)
			(importDone
				(if (and (Btst fWasWizard) [egoStats REVERSAL])
					(if (WizardBecomesPaladin)
						(= heroType PALADIN)
						(= origHeroType FIGHTER)
					else
						(= svCharType
							(= heroType
								(= origHeroType MAGIC_USER)
							)
						)
					)
				)
				(if
					(and
						validFile
						(< -1 (ChangeType))
						(< (ChangeType) 4)
					)
					(= heroType
						(= origHeroType
							newHeroType
						)
					)
					(if (== newHeroType PALADIN)
						(= origHeroType FIGHTER)
					)
				)
				(Palette PALIntensity 72 255 100)
				(if validFile
					(cSound fade: 0 2 5 1)
				)
				(curRoom newRoom: (if validFile 140 else 130))
			)
			(tryAgain
				(if
					(Print
						addText: N_ROOM V_DOIT C_TRY_AGAIN 1
						addButton: 1 N_ROOM V_DOIT C_YES 1 0 50
						addButton: 0 N_ROOM V_DOIT C_NO 1 60 50
						init:
					)
					(= bogus6 $79)
					(= bogus7 $86)
					(= bogus8 $43)
					(= bogus9 $88)
					(= bogus10 $AD)
					(= bogus11 $F0)
					(= bogus0 $A0)
					(= bogus1 $3E)
					(= bogus2 $2F)
					(= bogus3 $90)
					(= bogus4 $19)
					(= bogus5 $A3)
					(self changeState: waitABit2)
				else
					(= validFile 0)
					(self changeState: importDone)
				)
			)
		)
	)
)

(instance getFileName of Dialog
	
	(method (init &tmp left temp1)
		(= window GloryWindow)
		(= nsBottom 0)
		(Message MsgGet IMPORT N_ROOM V_DOIT C_IMPORT_TITLE 1 @str)
		(= text @str)
		(if (not (savedHeros init:))
			(return FALSE)
		)
		(savedHeros setSize: moveTo: MARGIN (+ nsBottom MARGIN) state: dExit)
		(= left (+ (savedHeros nsRight?) MARGIN))
		(Message MsgGet IMPORT N_ROOM V_DOIT C_CANCEL 1 @butBuf1)
		(Message MsgGet IMPORT N_ROOM V_DOIT C_CHANGE_DIR 1 @butBuf2)
		(Message MsgGet IMPORT N_ROOM V_DOIT C_IMPORT_BUTTON 1 @butBuf3)
		(importItem
			text: @butBuf3
			setSize:
			moveTo: left (+ 10 (savedHeros nsTop?))
			state: (if (savedHeros nFiles?) 3 else 0)
		)
		(changeDirItem
			text: @butBuf2
			setSize:
			moveTo: left (+ (importItem nsBottom?) 4)
			state: (& (changeDirItem state?) $fff7)
		)
		(cancelItem
			text: @butBuf1
			setSize:
			moveTo: left (+ (changeDirItem nsBottom?) 4)
			state: (& (changeDirItem state?) $fff7)
		)
		(self
			add: savedHeros importItem changeDirItem cancelItem
			setSize:
			center:
			open: MARGIN 15
		)
		(return TRUE)
	)
	
	(method (doit theName &tmp choice ret)
		(= curDir 0)			; Use default directory
		(if (not (self init:))
			(self dispose:)
			(return 0)
		)
		(repeat
			(= choice
				(super doit:
					(if (savedHeros nFiles?) importItem else changeDirItem)
				)
			)
			(cond
				((== choice importItem)
					(MakeFileName theName @curDir (savedHeros cursor?))
					(= ret 1)
					(break)
				)

				((== choice changeDirItem)
					(if (not (= ret (ChangeDir)))
						(break)
					)
				)

				((or (== choice 0) (== choice cancelItem))
					(= ret 0)
					(break)
				)
			)
		)
		(self dispose:)
		(return ret)
	)

	
	(method (dispose)
		(self eachElementDo: #dispose 1 release:)
		(super dispose:)
	)
)

(instance savedHeros of FileSelector
	(properties
		y 16
	)
	
	(method (init &tmp sep rc i j theText cp [savedHerosMask 80])
		(theGame setCursor: waitCursor)
		(= font smallFont)
		(= mask (MakeFileName @savedHerosMask @curDir {*.*}))
		(if (not (= rc (self readFiles: mask)))
			(Prints N_ROOM V_DOIT C_TOO_MANY_FILES 1)
		else
			(= theText text)
			(for ((= i 0)) (< i nFiles) ((++ i))
				(for ((= j 0)) (= cp (StrAt theText j)) ((++ j))
					(StrAt theText j
						(if (or (< cp `A) (> cp `Z))
							cp
						else
							(+ (- cp `A) `a)
						)
					)
				)
				(+= theText x)
			)
		)
		(theGame setCursor: normalCursor)
		(return rc)
	)
	
	(method (dispose)
		(super dispose: 1)
	)
)

(instance cancelItem of DButton)

(instance changeDirItem of DButton)

(instance importItem of DButton)

(instance quest of GameControls
	
	(method (init)
		(theGame setCursor: ARROW_CURSOR)
		((= window (GloryWindow new:))
			top: 50
			left: 72
			bottom: 155
			right: 248
			priority: 15
			yourself:
		)
		(self add: titleIcon yesIcon noIcon)
		(super init: &rest)
	)
)

(instance titleIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal DISABLED
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [buf 70])
		(Message MsgGet IMPORT N_ROOM V_DOIT C_WAS_WIZARD 1 @buf)
		(Display @buf p_width 165 p_at 5 3 p_color 17)
	)
)

(instance yesIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 70
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @butBuf4 p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= newHeroType MAGIC_USER)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @butBuf4 p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance noIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 85
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @butBuf5 p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= newHeroType FIGHTER)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @butBuf5 p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance choose of GameControls

	(method (init)
		(theGame setCursor: ARROW_CURSOR)
		((= window (GloryWindow new:))
			top: 40
			left: 72
			bottom: 155
			right: 248
			priority: 15
			yourself:
		)
		(self add: chooseIcon figIcon wizIcon thiIcon palIcon)
		(super init: &rest)
	)
)

(instance chooseIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal DISABLED
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [msgBuf 100] [charBuf 10] [temp110 110])
		(switch svCharType
			(FIGHTER
				(Message MsgGet IMPORT N_ROOM V_DOIT C_FIGHTER 1 @charBuf)
			)
			(MAGIC_USER
				(Message MsgGet IMPORT N_ROOM V_DOIT C_WIZARD 1 @charBuf)
			)
			(THIEF
				(Message MsgGet IMPORT N_ROOM V_DOIT C_THIEF 1 @charBuf)
			)
			(PALADIN
				(Message MsgGet IMPORT N_ROOM V_DOIT C_PALADIN 1 @charBuf)
			)
		)
		(Message MsgGet IMPORT N_ROOM V_DOIT C_CHOOSE_TYPE 1 @msgBuf)
		(Format @temp110 @msgBuf @charBuf)
		(Display @temp110 p_width 165 p_at 5 3 p_color 17)
	)
)

(instance figIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 55
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @butBuf6 p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= newHeroType FIGHTER)
		(choose state: (& (choose state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @butBuf6 p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance wizIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 70
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @butBuf7 p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= newHeroType MAGIC_USER)
		(choose state: (& (choose state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @butBuf7 p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance thiIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 85
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @butBuf8 p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= newHeroType THIEF)
		(choose state: (& (choose state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @butBuf8 p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance palIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 100
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @butBuf9 p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= newHeroType PALADIN)
		(choose state: (& (choose state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @butBuf9 p_at 20 (+ nsTop 3) p_color sColor)
	)
)
