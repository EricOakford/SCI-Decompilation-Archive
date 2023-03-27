;;; Sierra Script 1.0 - (do not remove this comment)
(script# IMPORT) ;54
(include game.sh) (include "54.shm")
(use Main)
(use GloryRm)
(use CharSave)
(use DButton)
(use String)
(use Print)
(use IconBar)
(use File)
(use System)

(public
	import 0
)

(local
	;; local data for restoring hero stats from QG2 or QG3
	statsKey =  $53
	svCharType
	svExperience
	svHighDinar
	svLowDinar
	svMiscEquip
	[codedStats QG3_NUM_ATTRIBS]
	;the following five locals were mistakenly decompiled
	; as part of codedStats, likely because they are unused
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
	heroFileName
	bigStr
	str
	butBuf1
	butBuf2
	butBuf3
	butBuf4
	statMap = [
		STR INT AGIL VIT LUCK WEAPON PARRY DODGE STEALTH PICK THROW CLIMB MAGIC COMM HONOR
		EXPER HEALTH STAMINA MANA OPEN DETMAGIC TRIGGER DAZZLE ZAP CALM FLAMEDART FETCH FORCEBOLT
		LEVITATE REVERSAL JUGGLE STAFF LIGHTNING HEALING]
		
	;; local data for restoring hero stats from QG1
	statsKeyQg1 =  $53
	svCharTypeQg1
	svHighGold
	svLowGold
	svScore
	svMiscEquipQg1
	[codedStatsQg1 QG1_NUM_ATTRIBS]
	;the following five locals were mistakenly decompiled
	; as part of codedStats, likely because they are unused
	svDaggerQg1
	svHealingPotion
	svManaPotion
	svStaminaPotion
	svGhostOil	
	bogus6 =  $79
	bogus7 =  $86
	check3
	check4
	bogus8 =  $43
	bogus9 =  $88
	bogus10 =  $AD
	bogus11 =  $F0
	checkSumKeyQg1 =  $CE
	statMapQg1 = [STR INT AGIL VIT LUCK WEAPON PARRY DODGE STEALTH PICK THROW CLIMB MAGIC
	EXPER HEALTH STAMINA MANA OPEN DETMAGIC TRIGGER DAZZLE ZAP CALM FLAMEDART FETCH]
	curDir
	newHeroType
	newStr
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

;; Bits in svMiscEquip (QG3)
(define DISPEL_BIT		$0001)
(define TINDER_BIT		$0002)
(define PIN_BIT			$0004)	;from QG2
(define DAGGER_BIT		$0008)
(define WATERSKIN_BIT	$0010)
(define BIRD_BIT		$0020)
(define BABA_BIT		$0040)	;from QG1
(define ROPE_BIT		$0080)
(define TOOL_BIT		$0100)
(define SWORD_BIT		$0200)
(define SHIELD_BIT		$0400)

;; Bits in svMiscEquip (QG2)
(define	QG2_FINESWORD_BIT 	$0001) ; fine sword
(define	QG2_FLAMESWORD_BIT	$0002) ; flaming sword
(define	QG2_COMPASS_BIT		$0003) ; compass
(define	QG2_PIN_BIT			$0004) ; spahire pin
(define	QG2_LAMP_BIT			$0008) ; brass lamp
(define	QG2_TOKEN_BIT		$0010) ; EOF token
(define	QG2_GLASSES_BIT		$0020) ; X-Ray Glasses

; things transferred from "Quest for Glory 1"
(define	QG2_BABA_BIT			$0030) ; Flag set from QG1
(define	QG2_SWORD_BIT		$0040) ; Sword
(define	QG2_CHAIN_BIT		$0080) ;
(define	QG2_PICK_BIT			$0100) ;
(define	QG2_TOOL_BIT			$0200) ;

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

(procedure (MakeZeroQG2 &tmp i)
	(for ((= i 1)) (< i COMM) ((++ i))
		(= [oldStats i] 0)
	)
	(= heroType 0)
	(for ((= i 0)) (< i iLastInvItem) ((++ i))
		((inventory at: i) amount: 0 owner: 0)
	)
	(= score 0)
	(userName copy: {xxxxxxxxxxy})
	(for ((= i 0)) (< i (+ QG2_NUM_ATTRIBS EXTRA_DATA)) ((++ i))
		(= [statsKey (+ i 1)] 0)
	)
)

(procedure (MakeZeroQG3 &tmp i)
	(for ((= i 1)) (< i COMM) ((++ i))
		(= [oldStats i] 0)
	)
	(= heroType 0)
	(for ((= i 0)) (< i iLastInvItem) ((++ i))
		((inventory at: i) amount: 0 owner: 0)
	)
	(= score 0)
	(userName copy: {xxxxxxxxxxy})
	(for ((= i 0)) (< i (+ QG3_NUM_ATTRIBS EXTRA_DATA)) ((++ i))
		(= [statsKey (+ i 1)] 0)
	)
)

(procedure (RestoreHero &tmp temp0 temp1 skillLevel)
	(MakeZeroQG2)
	(if (not (heroinfo open: fRead))
		(Message MsgGet IMPORT N_IMPORT V_DOIT C_CANT_FIND 1 (bigStr data?))
		(Printf (bigStr data?) (heroinfo name?))
		(return FALSE)
	)
	(= temp0 (String new: 400))
	(= temp1 (String new: 4))
	(heroinfo readString: userName 52)
	(heroinfo readString: bigStr 300)
	(if (and (== (bigStr size:) 86) (RestoreFromQG1))
		(temp0 dispose:)
		(temp1 dispose:)
		(return TRUE)
	)
	(for ((= skillLevel 0)) (< skillLevel 300) ((++ skillLevel))
		(bigStr at: skillLevel 0)
	)
	(heroinfo seek: 0 0)
	(heroinfo readString: userName 52)
	(heroinfo readString: bigStr 83)
	(heroinfo readString: temp1 3)
	(bigStr cat: temp1)
	(bigStr cat: temp1)
	(bigStr cat: temp1)
	(bigStr cat: temp1)
	(bigStr cat: temp1)
	(heroinfo readString: temp0 100)
	(bigStr cat: temp0)
	(if (RestoreFromQG2)
		(temp0 dispose:)
		(temp1 dispose:)
		(return TRUE)
	else
		(MakeZeroQG3)
		(= checkSumKey $D0)
		(heroinfo seek: 0 0)
		(heroinfo readString: bigStr 52)
		(heroinfo readString: userName 52)
		(heroinfo readString: bigStr 300)
		(if (RestoreFromQG3)
			(temp0 dispose:)
			(temp1 dispose:)
			(return TRUE)
		)
	)
	(Print addText: N_IMPORT V_DOIT C_INVALID_FILE_UNKNOWN 1 init:)
	(newStr dispose:)
	(= newStr (String new:))
	(temp0 dispose:)
	(temp1 dispose:)
	(return FALSE)
)

(procedure (localproc_079e param1 param2 param3 param4)
	(return
		(+
			(ConvWord param3 param4)
			(* (ConvWord param1 param2) 100)
		)
	)
)

(procedure (ConvByte ascii)
	(return
		(cond 
			((or (== ascii 32) (== ascii 0))
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

(procedure (ConvWord ascii param2 &tmp [temp0 2])
	(return
		(|
			(<< (ConvByte ascii) 4)
			(ConvByte param2)
		)
	)
)

(procedure (MakeFileName name path root &tmp sep pathLen)
	(if (= pathLen (path size:))
		(= sep (path at: (- pathLen 1)))
		(if (OneOf sep 92 58)
			(name format: {%s%s} path root)
		else
			(name format: {%s%c%s} path 92 root)
		)
	else
		(name copy: root)
	)
	(name data?)
)

(procedure (GetCurSaveDir dir &tmp newDir result temp2)
	(= result 0)
	(= newDir (String new:))
	(theGame handsOn:)
	(repeat
		(IconBarCursor view: 942 loop: 0 cel: 14 init:)
		(if (not (HaveMouse))
			(theGame setCursor: IconBarCursor TRUE oldCurX oldCurY)
		else
			(theGame setCursor: IconBarCursor TRUE)
		)
		(= result
			(Print
				font: 999
				largeAlp: 0
				addText: N_GET_FILE_NAME NULL C_NEW_DIRECTORY 1 0 10
				addEdit: (newDir copy: dir) 30 0 25 dir
				addButton: 1 N_GET_FILE_NAME NULL NULL 1 0 40
				addButton: 0 N_GET_FILE_NAME NULL NULL 2 0 58
				init:
			)
		)
		(theGame handsOff:)
		(if (not result)
			(break)
		)
		(if (not (newDir size:))
			(GetCWD (newDir data?))
			(newStr dispose:)
			(= newStr (String new:))
		)
		(if (ValidPath (newDir data?))
			(curDir copy: newDir)
			(newStr dispose:)
			(= newStr (String new:))
			(break)
		)
		(Message MsgGet IMPORT N_IMPORT V_DOIT C_INVALID_DIR 1 (bigStr data?))
		(Printf (bigStr data?) (newDir data?))
		(newStr dispose:)
		(= newStr (String new:))
	)
	(newDir dispose:)
	(return result)
)

(procedure (RestoreFromQG2 &tmp whichSkill i j)
	;NOTE: This decompiled the number as 52, which would translate
	; to (+ QG3_NUM_ATTRIBS EXTRA_DATA), which is for QFG3, not QFG2. This has been fixed.
	(for ((= whichSkill 0) (= j 0)) (< whichSkill (+ QG2_NUM_ATTRIBS EXTRA_DATA)) ((++ whichSkill) (+= j 2))
		(= [statsKey (+ whichSkill 1)]
			(ConvWord
				(bigStr at: j)
				(bigStr at: (+ j 1))
			)
		)
	)
	(for ((= whichSkill (+ QG2_NUM_ATTRIBS EXTRA_DATA))) (< 0 whichSkill) ((-- whichSkill))
		(^= [statsKey whichSkill] (& [statsKey (- whichSkill 1)] 255))
	)
	;NOTE: The checksum code uses the correct number, 40
	; which translates to (+ QG2_NUM_ATTRIBS CHECK_DATA)
	(= check1 218)
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
	(if (or (!= check1 checkSum1) (!= check2 checkSum2))
		(return FALSE)
	)
	;this decompiled number of attributes to 34, which would be true for QFG3,
	; but not QFG2, which was 30. It's been fixed.
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
				(return FALSE)
			)
		)
	)
	(if (== svCharType FIGHTER)
		(if (and [egoStats ZAP] [egoStats REVERSAL])
			(= svCharType MAGIC_USER)
		else
			(= i 0)
			(for ((= whichSkill 0)) (< whichSkill COMM) ((++ whichSkill))
				(if [egoStats whichSkill]
					(++ i)
				)
			)
			(if (or (not [egoStats PARRY]) (== i COMM))
				(= svCharType THIEF)
			)
		)
	)
	(= heroType
		(= origHeroType svCharType)
	)
	(if (and (not (& svMiscEquip QG2_CHAIN_BIT)) (== heroType PALADIN))
		(Bset fWasWizard)
	)
	(if (== heroType PALADIN)
		(= origHeroType FIGHTER)
	)
	(if (&= svMiscEquip QG2_BABA_BIT)
		(Bset fBabaFrog)
	)
	(return TRUE)
)

(procedure (RestoreFromQG1 &tmp whichSkill i)
	(for ((= whichSkill 0) (= i 0)) (< whichSkill (+ QG1_NUM_ATTRIBS EXTRA_DATA)) ((++ whichSkill) (+= i 2))
		(= [statsKeyQg1 (+ whichSkill 1)]
			(ConvWord
				(bigStr at: i)
				(bigStr at: (+ i 1))
			)
		)
	)
	(for ((= whichSkill (+ QG1_NUM_ATTRIBS EXTRA_DATA))) (< 0 whichSkill) ((-- whichSkill))
		(^= [statsKeyQg1 whichSkill]
			(& [statsKeyQg1 (- whichSkill 1)] 127)
		)
	)
	(= check1 checkSumKeyQg1)
	(for ((= whichSkill 0)) (< whichSkill (+ QG1_NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
		(= [statsKeyQg1 (+ whichSkill 1)]
			(& [statsKeyQg1 (+ whichSkill 1)] 127)
		)
		(+= check1 [statsKeyQg1 (+ whichSkill 1)])
	)
	(= check2 0)
	(for ((= whichSkill 1)) (< whichSkill (+ QG1_NUM_ATTRIBS CHECK_DATA)) ((+= whichSkill 2))
		(= [statsKeyQg1 (+ whichSkill 1)]
			(& [statsKeyQg1 (+ whichSkill 1)] 127)
		)
		(+= check2 [statsKeyQg1 (+ whichSkill 1)])
	)
	(&= check1 127)
	(&= check2 127)
	(if (or (!= check1 check3) (!= check2 check4))
		(return FALSE)
	)
	(for ((= whichSkill 0)) (< whichSkill QG1_NUM_ATTRIBS) ((++ whichSkill))
		(= [egoStats [statMapQg1 whichSkill]]
			(* 2 [codedStatsQg1 whichSkill])
		)
		(if
			(not
				(if (< MAGIC [statMapQg1 whichSkill]) (< [statMapQg1 whichSkill] OPEN))
			)
			(if
				(not
					(if (<= 0 [codedStatsQg1 whichSkill])
						(<= [codedStatsQg1 whichSkill] 300)
					)
				)
				(return FALSE)
			)
		)
	)
	(= heroType
		(= origHeroType svCharTypeQg1)
	)
	(= [egoStats COMM]
		(/ (+ (* [egoStats INT] 2) [egoStats LUCK]) 3)
	)
	(= [egoStats HONOR] 100)
	(if (& svMiscEquipQg1 QG1_BABA_BIT)
		(Bset fBabaFrog)
	)
	(return TRUE)
)

(procedure (ChangeType &tmp curTypeBuf newTypeBuf)
	(= curTypeBuf (String new:))
	(Message MsgGet IMPORT N_IMPORT V_DOIT C_CHANGE_TYPE 1 (curTypeBuf data?))
	(= newTypeBuf (String new:))
	(switch svCharType
		(FIGHTER
			(Message MsgGet IMPORT N_IMPORT V_DOIT C_FIGHTER 1 (newTypeBuf data?))
		)
		(MAGIC_USER
			(Message MsgGet IMPORT N_IMPORT V_DOIT C_WIZARD 1 (newTypeBuf data?))
		)
		(THIEF
			(Message MsgGet IMPORT N_IMPORT V_DOIT C_THIEF 1 (newTypeBuf data?))
		)
		(PALADIN
			(Message MsgGet IMPORT N_IMPORT V_DOIT C_PALADIN 1 (newTypeBuf data?))
		)
	)
	(theGame handsOn:)
	(IconBarCursor view: 942 loop: 0 cel: 14 init:)
	(if (not (HaveMouse))
		(theGame setCursor: IconBarCursor TRUE oldCurX oldCurY)
	else
		(theGame setCursor: IconBarCursor TRUE)
	)
	(= newHeroType
		(Print
			addTextF: (curTypeBuf data?) (newTypeBuf data?)
			font: 999
			addButton: 4 N_IMPORT V_DOIT C_CONTINUE 1 0 65 54
			addButton: 0 N_IMPORT V_DOIT C_FIGHTER 1 70 65 54
			addButton: 1 N_IMPORT V_DOIT C_WIZARD 1 70 85 54
			addButton: 2 N_IMPORT V_DOIT C_THIEF 1 130 65 54
			addButton: 3 N_IMPORT V_DOIT C_PALADIN 1 130 85 54
			init:
		)
	)
	(theGame handsOff:)
	(curTypeBuf dispose:)
	(newTypeBuf dispose:)
	(return newHeroType)
)

(procedure (RestoreFromQG3 &tmp whichSkill i)
	(for ((= whichSkill 0) (= i 0)) (< whichSkill (+ QG3_NUM_ATTRIBS EXTRA_DATA)) ((++ whichSkill) (+= i 4))
		(= [statsKey (+ whichSkill 1)]
			(localproc_079e
				(bigStr at: i)
				(bigStr at: (+ i 1))
				(bigStr at: (+ i 2))
				(bigStr at: (+ i 3))
			)
		)
	)
	(for ((= whichSkill (+ QG3_NUM_ATTRIBS EXTRA_DATA))) (< 0 whichSkill) ((-- whichSkill))
		(^= [statsKey whichSkill] [statsKey (- whichSkill 1)])
	)
	(= check1 checkSumKey)
	(= whichSkill 0)
	(for ((= whichSkill 0)) (< whichSkill (+ QG3_NUM_ATTRIBS CHECK_DATA 1)) ((+= whichSkill 2))
		(= [statsKey (+ whichSkill 1)] [statsKey (+ whichSkill 1)])
		(+= check1 [statsKey (+ whichSkill 1)])
	)
	(= check2 0)
	(for ((= whichSkill 1)) (< whichSkill (+ QG3_NUM_ATTRIBS CHECK_DATA 1)) ((+= whichSkill 2))
		(= [statsKey (+ whichSkill 1)] [statsKey (+ whichSkill 1)])
		(+= check2 [statsKey (+ whichSkill 1)])
	)
	(for ((= whichSkill 0)) (< whichSkill QG3_NUM_ATTRIBS) ((++ whichSkill))
		(= [egoStats [statMap whichSkill]] [codedStats (- whichSkill 1)])
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
				(cond 
					((> [codedStats whichSkill] 300)
						(= [codedStats whichSkill] 300)
					)
					((< [codedStats whichSkill] 0)
						(= [codedStats whichSkill] 0)
					)
				)
			)
		)
	)
	(if
		(and
			(== svCharType FIGHTER)
			[egoStats [statMap ZAP]]
			[egoStats [statMap REVERSAL]]
		)
		(= svCharType MAGIC_USER)
	)
	(= heroType
		(= origHeroType svCharType)
	)	
	(if (== heroType PALADIN)
		(= origHeroType FIGHTER)
	)
	(if (& svMiscEquip BIRD_BIT)
		(Bset fBoughtBlackBird)
	)
	(return TRUE)
)

(instance import of GloryRm
	(properties
		picture 130
	)
	
	(method (init)
		(theGame handsOff:)
		(= curDir (String new:))
		(GetCWD (curDir data?))
		(= bigStr (String newWith: 301))
		(= heroFileName (String new:))
		(= newStr (String new:))
		(super init: &rest)
		(self setScript: importHero)
	)
	
	(method (dispose)
		(curDir dispose:)
		(bigStr dispose:)
		(heroFileName dispose:)
		(super dispose:)
	)
)

(instance heroinfo of File
	(properties
		name "glory2.sav"
	)
)

(instance importHero of Script
	
	(method (changeState newState &tmp temp0 answer newStr_2 curDirSize temp4)
		(switch (= state newState)
			(waitABit
				(= cycles 3)
			)
			(askRestore
				(Message MsgGet IMPORT N_IMPORT V_DOIT C_FILE_NAME 1 (heroFileName data?))
				(messager say: N_IMPORT NULL NULL 0 self)
			)
			(waitABit2
				(= cycles 2)
			)
			(getInfoFileName
				(= newStr_2 (String new:))
				(theGame handsOn:)
				(IconBarCursor view: 942 loop: 0 cel: 14 init:)
				(if (not (HaveMouse))
					(theGame setCursor: IconBarCursor TRUE oldCurX oldCurY)
				else
					(theGame setCursor: IconBarCursor TRUE)
				)
				(= answer
					(Print
						font: 999
						largeAlp: 0
						addText: N_IMPORT V_DOIT C_IMPORT_TITLE 1 5 -5 54
						addFSelector: 0 15 8 (MakeFileName newStr_2 curDir {*.sav})
						classButton: MyButton
						addButton: 1 N_IMPORT V_DOIT C_IMPORT_BUTTON 1 126 18 54
						classButton: changeButton
						addButton: 2 N_IMPORT V_DOIT C_CHANGE_DIR 1 126 40 54
						classButton: MyButton
						addButton: 0 N_IMPORT V_DOIT C_CANCEL 1 126 70 54
						init:
					)
				)
				(theGame handsOff:)
				(newStr_2 dispose:)
				(switch answer
					(0
						(curRoom newRoom: 100)
					)
					(2
						(GetCurSaveDir curDir)
						(self changeState: 2)
					)
					(else 
						(newStr cat: curDir)
						(= curDirSize (curDir size:))
						(= temp4 (curDir at: (- curDirSize 1)))
						(if (OneOf temp4 92 58) 0 else (newStr cat: {\\}))
						(newStr cat: saveFileSelText)
						(heroinfo name: (newStr data?))
					)
				)
				(= cycles 2)
			)
			(restoreFile
				(if (= validFile (RestoreHero))
					(= cycles 2)
				else
					(self changeState: tryAgain)
				)
			)
			(readComplete
				(messager say: N_IMPORT V_DOIT C_IMPORT_SUCCESS 0 self)
			)
			(importDone
				(if
					(and
						validFile
						(< -1 (ChangeType))
						(< (ChangeType) 4)
					)
					(= heroType
						(= origHeroType newHeroType)
					)
					(if (== newHeroType PALADIN)
						(= origHeroType FIGHTER)
					)
				)
				(curRoom newRoom: (if validFile 140 else 100))
			)
			(tryAgain
				(theGame handsOn:)
				(IconBarCursor view: 942 loop: 0 cel: 14 init:)
				(if (not (HaveMouse))
					(theGame setCursor: IconBarCursor TRUE oldCurX oldCurY)
				else
					(theGame setCursor: IconBarCursor TRUE)
				)
				(if
					(Print
						addText: N_IMPORT V_DOIT C_TRY_AGAIN 1
						font: 999
						addButton: 1 N_IMPORT V_DOIT C_YES 1 0 50
						addButton: 0 N_IMPORT V_DOIT C_NO 1 60 50
						init:
					)
					(theGame handsOff:)
					(newStr dispose:)
					(= newStr (String new:))
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
					(theGame handsOff:)
					(newStr dispose:)
					(= newStr (String new:))
					(= validFile 0)
					(self changeState: importDone)
				)
			)
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

(instance changeButton of MyButton
	(properties
		loop 1
	)
)