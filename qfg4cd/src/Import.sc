;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include sci.sh)
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
	statsKey =  83
	svCharType
	[svExperience 3]
	svHighDinar
	[svLowDinar 39]
	svMiscEquip =  160
	codedStats =  62
	svDagger
	svHealingPill
	svManaPill =  47
	svStaminaPill =  144
	svPoisonCurePill =  25
	bogus0 =  163
	bogus1 =  218
	checkSum1
	checkSum2
	bogus2
	bogus3
	bogus4
	[bogus5 4]
	[checkSumKey 34] = [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 41]
	check1 =  83
	check2
	[validFile 3]
	heroFileName
	[bigStr 30]
	str =  121
	butBuf1 =  134
	butBuf2
	butBuf3
	butBuf4 =  67
	statMap =  136
	statsKeyQg1 =  173
	svCharTypeQg1 =  240
	svHighGold =  206
	[svLowGold 25] = [0 1 2 3 4 5 6 7 8 9 10 11 12 16 17 18 19 20 21 22 23 24 25 26 27]
	svScore
	svMiscEquipQg1
	codedStatsQg1
)
(procedure (localproc_04bf &tmp temp0)
	(= temp0 1)
	(while (< temp0 13)
		(= [oldStats temp0] 0)
		(++ temp0)
	)
	(= heroType 0)
	(= temp0 0)
	(while (< temp0 57)
		((inventory at: temp0) amount: 0 owner: 0)
		(++ temp0)
	)
	(= score 0)
	(userName copy: {xxxxxxxxxxy})
	(= temp0 0)
	(while (< temp0 48)
		(= [statsKey (+ temp0 1)] 0)
		(++ temp0)
	)
)

(procedure (localproc_0528 &tmp temp0)
	(= temp0 1)
	(while (< temp0 13)
		(= [oldStats temp0] 0)
		(++ temp0)
	)
	(= heroType 0)
	(= temp0 0)
	(while (< temp0 57)
		((inventory at: temp0) amount: 0 owner: 0)
		(++ temp0)
	)
	(= score 0)
	(userName copy: {xxxxxxxxxxy})
	(= temp0 0)
	(while (< temp0 52)
		(= [statsKey (+ temp0 1)] 0)
		(++ temp0)
	)
)

(procedure (localproc_0591 &tmp temp0 temp1 temp2)
	(localproc_04bf)
	(if (not (glory2_sav open: 1))
		(Message msgGET 54 2 6 7 1 (bogus4 data?))
		(Printf (bogus4 data?) (glory2_sav name?))
		(return 0)
	)
	(= temp0 (Str new: 400))
	(= temp1 (Str new: 4))
	(glory2_sav readString: userName 52)
	(glory2_sav readString: bogus4 300)
	(if (and (== (bogus4 size:) 86) (localproc_0c3a))
		(temp0 dispose:)
		(temp1 dispose:)
		(return 1)
	)
	(= temp2 0)
	(while (< temp2 300)
		(bogus4 at: temp2 0)
		(++ temp2)
	)
	(glory2_sav seek: 0 0)
	(glory2_sav readString: userName 52)
	(glory2_sav readString: bogus4 83)
	(glory2_sav readString: temp1 3)
	(bogus4 cat: temp1)
	(bogus4 cat: temp1)
	(bogus4 cat: temp1)
	(bogus4 cat: temp1)
	(bogus4 cat: temp1)
	(glory2_sav readString: temp0 100)
	(bogus4 cat: temp0)
	(if (localproc_0a84)
		(temp0 dispose:)
		(temp1 dispose:)
		(return 1)
	else
		(localproc_0528)
		(= bogus1 208)
		(glory2_sav seek: 0 0)
		(glory2_sav readString: bogus4 52)
		(glory2_sav readString: userName 52)
		(glory2_sav readString: bogus4 300)
		(if (localproc_0f42)
			(temp0 dispose:)
			(temp1 dispose:)
			(return 1)
		)
	)
	(Print addText: 2 6 25 1 init:)
	(codedStatsQg1 dispose:)
	(= codedStatsQg1 (Str new:))
	(temp0 dispose:)
	(temp1 dispose:)
	(return 0)
)

(procedure (localproc_079e param1 param2 param3 param4)
	(return
		(+
			(localproc_07e6 param3 param4)
			(* (localproc_07e6 param1 param2) 100)
		)
	)
)

(procedure (localproc_07b7 param1)
	(return
		(cond 
			((or (== param1 32) (== param1 0)) (return 0))
			((and (<= 48 param1) (<= param1 57)) (return (- param1 48)))
			(else (return (- param1 87)))
		)
	)
)

(procedure (localproc_07e6 param1 param2 &tmp [temp0 2])
	(return
		(|
			(<< (localproc_07b7 param1) $0004)
			(localproc_07b7 param2)
		)
	)
)

(procedure (localproc_07fd param1 param2 param3 &tmp temp0 temp1)
	(if (= temp1 (param2 size:))
		(= temp0 (param2 at: (- temp1 1)))
		(if (OneOf temp0 92 58)
			(param1 format: {%s%s} param2 param3)
		else
			(param1 format: {%s%c%s} param2 92 param3)
		)
	else
		(param1 copy: param3)
	)
	(param1 data?)
)

(procedure (localproc_0908 param1 &tmp newStr temp1 temp2)
	(= temp1 0)
	(= newStr (Str new:))
	(theGame handsOn:)
	(repeat
		(IconBarCursor view: 942 loop: 0 cel: 14 init:)
		(if (not (HaveMouse))
			(theGame setCursor: IconBarCursor 1 oldCurX oldCurY)
		else
			(theGame setCursor: IconBarCursor 1)
		)
		(= temp1
			(Print
				font: 999
				largeAlp: 0
				addText: 1 0 1 1 0 10
				addEdit: (newStr copy: param1) 30 0 25 param1
				addButton: 1 1 0 0 1 0 40
				addButton: 0 1 0 0 2 0 58
				init:
			)
		)
		(theGame handsOff:)
		(if (not temp1) (break))
		(if (not (newStr size:))
			(GetCWD (newStr data?))
			(codedStatsQg1 dispose:)
			(= codedStatsQg1 (Str new:))
		)
		(if (ValidPath (newStr data?))
			(svScore copy: newStr)
			(codedStatsQg1 dispose:)
			(= codedStatsQg1 (Str new:))
			(break)
		)
		(Message msgGET 54 2 6 15 1 (bogus4 data?))
		(Printf (bogus4 data?) (newStr data?))
		(codedStatsQg1 dispose:)
		(= codedStatsQg1 (Str new:))
	)
	(newStr dispose:)
	(return temp1)
)

(procedure (localproc_0a84 &tmp temp0 temp1 temp2)
	(= temp0 0)
	(= temp2 0)
	(while (< temp0 52)
		(= [statsKey (+ temp0 1)]
			(localproc_07e6
				(bogus4 at: temp2)
				(bogus4 at: (+ temp2 1))
			)
		)
		(++ temp0)
		(= temp2 (+ temp2 2))
	)
	(= temp0 52)
	(while (< 0 temp0)
		(= [statsKey temp0]
			(^ [statsKey temp0] (& [statsKey (- temp0 1)] $00ff))
		)
		(-- temp0)
	)
	(= checkSum1 218)
	(= temp0 0)
	(while (< temp0 40)
		(= [statsKey (+ temp0 1)]
			(& [statsKey (+ temp0 1)] $00ff)
		)
		(= checkSum1 (+ checkSum1 [statsKey (+ temp0 1)]))
		(= temp0 (+ temp0 2))
	)
	(= checkSum2 0)
	(= temp0 1)
	(while (< temp0 40)
		(= [statsKey (+ temp0 1)]
			(& [statsKey (+ temp0 1)] $00ff)
		)
		(= checkSum2 (+ checkSum2 [statsKey (+ temp0 1)]))
		(= temp0 (+ temp0 2))
	)
	(= checkSum1 (& checkSum1 $00ff))
	(= checkSum2 (& checkSum2 $00ff))
	(if
		(or
			(!= checkSum1 svDagger)
			(!= checkSum2 svHealingPill)
		)
		(return 0)
	)
	(= temp0 0)
	(while (< temp0 34)
		(= [egoStats [checkSumKey temp0]] [svLowDinar temp0])
		(if
			(not
				(if (< 13 [checkSumKey temp0])
					(< [checkSumKey temp0] 20)
				)
			)
			(if
				(not
					(if (<= 0 [svLowDinar temp0])
						(<= [svLowDinar temp0] 300)
					)
				)
				(return 0)
			)
		)
		(++ temp0)
	)
	(if (== svCharType 0)
		(if (and [egoStats 24] [egoStats 30])
			(= svCharType 1)
		else
			(= temp1 0)
			(= temp0 0)
			(while (< temp0 13)
				(if [egoStats temp0] (++ temp1))
				(++ temp0)
			)
			(if (or (not [egoStats 6]) (== temp1 13))
				(= svCharType 2)
			)
		)
	)
	(= heroType (= origHeroType svCharType))
	(if
	(and (not (& svHighDinar $0080)) (== heroType 3))
		(Bset 29)
	)
	(if (== heroType 3) (= origHeroType 0))
	(if (== (& svHighDinar $0030) 48) (Bset 22))
	(return 1)
)

(procedure (localproc_0c3a &tmp temp0 temp1)
	(= temp0 0)
	(= temp1 0)
	(while (< temp0 43)
		(= [check1 (+ temp0 1)]
			(localproc_07e6
				(bogus4 at: temp1)
				(bogus4 at: (+ temp1 1))
			)
		)
		(++ temp0)
		(= temp1 (+ temp1 2))
	)
	(= temp0 43)
	(while (< 0 temp0)
		(= [check1 temp0]
			(^ [check1 temp0] (& [check1 (- temp0 1)] $007f))
		)
		(-- temp0)
	)
	(= checkSum1 svHighGold)
	(= temp0 0)
	(while (< temp0 35)
		(= [check1 (+ temp0 1)] (& [check1 (+ temp0 1)] $007f))
		(= checkSum1 (+ checkSum1 [check1 (+ temp0 1)]))
		(= temp0 (+ temp0 2))
	)
	(= checkSum2 0)
	(= temp0 1)
	(while (< temp0 35)
		(= [check1 (+ temp0 1)] (& [check1 (+ temp0 1)] $007f))
		(= checkSum2 (+ checkSum2 [check1 (+ temp0 1)]))
		(= temp0 (+ temp0 2))
	)
	(= checkSum1 (& checkSum1 $007f))
	(= checkSum2 (& checkSum2 $007f))
	(if
	(or (!= checkSum1 butBuf2) (!= checkSum2 butBuf3))
		(return 0)
	)
	(= temp0 0)
	(while (< temp0 25)
		(= [egoStats [svLowGold temp0]] (* 2 [bigStr temp0]))
		(if
			(not
				(if (< 12 [svLowGold temp0]) (< [svLowGold temp0] 20))
			)
			(if
				(not
					(if (<= 0 [bigStr temp0]) (<= [bigStr temp0] 300))
				)
				(return 0)
			)
		)
		(++ temp0)
	)
	(= heroType (= origHeroType check2))
	(= [egoStats 13]
		(/ (+ (* [egoStats 1] 2) [egoStats 4]) 3)
	)
	(= [egoStats 14] 100)
	(if (& heroFileName $0020) (Bset 22))
	(return 1)
)

(procedure (localproc_0d9f &tmp newStr newStr_2)
	(= newStr (Str new:))
	(Message msgGET 54 2 6 23 1 (newStr data?))
	(= newStr_2 (Str new:))
	(switch svCharType
		(0
			(Message msgGET 54 2 6 18 1 (newStr_2 data?))
		)
		(1
			(Message msgGET 54 2 6 21 1 (newStr_2 data?))
		)
		(2
			(Message msgGET 54 2 6 19 1 (newStr_2 data?))
		)
		(3
			(Message msgGET 54 2 6 22 1 (newStr_2 data?))
		)
	)
	(theGame handsOn:)
	(IconBarCursor view: 942 loop: 0 cel: 14 init:)
	(if (not (HaveMouse))
		(theGame setCursor: IconBarCursor 1 oldCurX oldCurY)
	else
		(theGame setCursor: IconBarCursor 1)
	)
	(= svMiscEquipQg1
		(Print
			addTextF: (newStr data?) (newStr_2 data?)
			font: 999
			addButton: 4 2 6 26 1 0 65 54
			addButton: 0 2 6 18 1 70 65 54
			addButton: 1 2 6 21 1 70 85 54
			addButton: 2 2 6 19 1 130 65 54
			addButton: 3 2 6 22 1 130 85 54
			init:
		)
	)
	(theGame handsOff:)
	(newStr dispose:)
	(newStr_2 dispose:)
	(return svMiscEquipQg1)
)

(procedure (localproc_0f42 &tmp temp0 temp1)
	(= temp0 0)
	(= temp1 0)
	(while (< temp0 52)
		(= [statsKey (+ temp0 1)]
			(localproc_079e
				(bogus4 at: temp1)
				(bogus4 at: (+ temp1 1))
				(bogus4 at: (+ temp1 2))
				(bogus4 at: (+ temp1 3))
			)
		)
		(++ temp0)
		(= temp1 (+ temp1 4))
	)
	(= temp0 52)
	(while (< 0 temp0)
		(= [statsKey temp0]
			(^ [statsKey temp0] [statsKey (- temp0 1)])
		)
		(-- temp0)
	)
	(= checkSum1 bogus1)
	(= temp0 0)
	(while (< temp0 45)
		(= [statsKey (+ temp0 1)] [statsKey (+ temp0 1)])
		(= checkSum1 (+ checkSum1 [statsKey (+ temp0 1)]))
		(= temp0 (+ temp0 2))
	)
	(= checkSum2 0)
	(= temp0 1)
	(while (< temp0 45)
		(= [statsKey (+ temp0 1)] [statsKey (+ temp0 1)])
		(= checkSum2 (+ checkSum2 [statsKey (+ temp0 1)]))
		(= temp0 (+ temp0 2))
	)
	(= temp0 0)
	(while (< temp0 34)
		(= [egoStats [checkSumKey temp0]]
			[svLowDinar (- temp0 1)]
		)
		(if
			(not
				(if (< 13 [checkSumKey temp0])
					(< [checkSumKey temp0] 20)
				)
			)
			(if
				(not
					(if (<= 0 [svLowDinar temp0])
						(<= [svLowDinar temp0] 300)
					)
				)
				(cond 
					((> [svLowDinar temp0] 300) (= [svLowDinar temp0] 300))
					((< [svLowDinar temp0] 0) (= [svLowDinar temp0] 0))
				)
			)
		)
		(++ temp0)
	)
	(if
		(and
			(== svCharType 0)
			[egoStats [checkSumKey 24]]
			[egoStats [checkSumKey 30]]
		)
		(= svCharType 1)
	)
	(if
	(== (= heroType (= origHeroType svCharType)) 3)
		(= origHeroType 0)
	)
	(if (& svHighDinar $0020) (Bset 387))
	(return 1)
)

(instance import of GloryRm
	(properties
		picture 130
	)
	
	(method (init)
		(theGame handsOff:)
		(= svScore (Str new:))
		(GetCWD (svScore data?))
		(= bogus4 (Str newWith: 301))
		(= bogus3 (Str new:))
		(= codedStatsQg1 (Str new:))
		(super init: &rest)
		(self setScript: importHero)
	)
	
	(method (dispose)
		(svScore dispose:)
		(bogus4 dispose:)
		(bogus3 dispose:)
		(super dispose:)
	)
)

(instance glory2_sav of File
	(properties
		name "glory2.sav"
	)
)

(instance importHero of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 newStr svScoreSize temp4)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(Message msgGET 54 2 6 2 1 (bogus3 data?))
				(messager say: 2 0 0 0 self)
			)
			(2 (= cycles 2))
			(3
				(= newStr (Str new:))
				(theGame handsOn:)
				(IconBarCursor view: 942 loop: 0 cel: 14 init:)
				(if (not (HaveMouse))
					(theGame setCursor: IconBarCursor 1 oldCurX oldCurY)
				else
					(theGame setCursor: IconBarCursor 1)
				)
				(= temp1
					(Print
						font: 999
						largeAlp: 0
						addText: 2 6 8 1 5 -5 54
						addFSelector: 0 15 8 (localproc_07fd newStr svScore {*.sav})
						classButton: MyButton
						addButton: 1 2 6 12 1 126 18 54
						classButton: changeButton
						addButton: 2 2 6 11 1 126 40 54
						classButton: MyButton
						addButton: 0 2 6 10 1 126 70 54
						init:
					)
				)
				(theGame handsOff:)
				(newStr dispose:)
				(switch temp1
					(0 (curRoom newRoom: 100))
					(2
						(localproc_0908 svScore)
						(self changeState: 2)
					)
					(else 
						(codedStatsQg1 cat: svScore)
						(= svScoreSize (svScore size:))
						(= temp4 (svScore at: (- svScoreSize 1)))
						(if (OneOf temp4 92 58)
							0
						else
							(codedStatsQg1 cat: {\\})
						)
						(codedStatsQg1 cat: saveFileSelText)
						(glory2_sav name: (codedStatsQg1 data?))
					)
				)
				(= cycles 2)
			)
			(4
				(if (= bogus2 (localproc_0591))
					(= cycles 2)
				else
					(self changeState: 7)
				)
			)
			(5 (messager say: 2 6 3 0 self))
			(6
				(if
					(and
						bogus2
						(< -1 (localproc_0d9f))
						(< (localproc_0d9f) 4)
					)
					(= heroType (= origHeroType svMiscEquipQg1))
					(if (== svMiscEquipQg1 3) (= origHeroType 0))
				)
				(curRoom newRoom: (if bogus2 140 else 100))
			)
			(7
				(theGame handsOn:)
				(IconBarCursor view: 942 loop: 0 cel: 14 init:)
				(if (not (HaveMouse))
					(theGame setCursor: IconBarCursor 1 oldCurX oldCurY)
				else
					(theGame setCursor: IconBarCursor 1)
				)
				(if
					(Print
						addText: 2 6 4 1
						font: 999
						addButton: 1 2 6 5 1 0 50
						addButton: 0 2 6 6 1 60 50
						init:
					)
					(theGame handsOff:)
					(codedStatsQg1 dispose:)
					(= codedStatsQg1 (Str new:))
					(= str 121)
					(= butBuf1 134)
					(= butBuf4 67)
					(= statMap 136)
					(= statsKeyQg1 173)
					(= svCharTypeQg1 240)
					(= svMiscEquip 160)
					(= codedStats 62)
					(= svManaPill 47)
					(= svStaminaPill 144)
					(= svPoisonCurePill 25)
					(= bogus0 163)
					(self changeState: 2)
				else
					(theGame handsOff:)
					(codedStatsQg1 dispose:)
					(= codedStatsQg1 (Str new:))
					(= bogus2 0)
					(self changeState: 6)
				)
			)
		)
	)
)

(class MyButton of DButton
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0003
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -546
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0000
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type $0002
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 1
		fore 0
		back 0
		skip 254
		font 999
		borderColor 0
		dimmed 0
		rects 0
	)
)

(instance changeButton of MyButton
	(properties
		loop 1
	)
)
