;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include sci.sh)
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

(local
	statsKey =  83
	svCharType
	svHighCrown
	svLowCrown
	svMiscEquip
	[codedStats 48]
	svRations =  25
	svDaggers =  190
	svOil
	svHealingPotion
	svManaPotion =  55
	svPoisonCurePotion =  109
	bogus0 =  196
	bogus1 =  242
	checkSum1 =  208
	checkSum2
	bogus2
	bogus3
	bogus4
	bogus5
	checkSumKey
	check1
)
(instance CharSave of GloryRm
	(properties)
	
	(method (init)
		(super init: &rest)
		(= bogus3 (Str new:))
		(= bogus4 (Str new:))
		(= bogus5 (Str new:))
		((ScriptID 15) doit:)
		(keyDownHandler add: self)
		(User canControl: 0 canInput: 0)
		(self setScript: saveHero)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(LoadMany 0 -543 -592)
		(super dispose:)
	)
)

(instance glory4_sav of File
	(properties
		name "glory4.sav"
	)
)

(instance saveHero of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3 temp4)
		(switch (= state newState)
			(0
				(Message msgGET 52 2 0 10 1 (bogus3 data?))
				(if (>= score possibleScore)
					(messager say: 2 0 8 1 self)
				else
					(Message msgGET 52 2 0 4 1 (bogus5 data?))
					(bogus4 format: (bogus5 data?) score possibleScore)
					(Print addText: bogus4 init: self)
				)
				(= register 1)
			)
			(1
				(messager say: 2 0 5 0 self)
				(= register 1)
			)
			(2
				(if
					(Print
						addText: 2 0 11 1 0 0 52
						font: 999
						addEdit: bogus3 30 0 30 bogus3
						init:
					)
					(glory4_sav name: (bogus3 data?))
					(= cycles 2)
				else
					(self changeState: 8)
				)
			)
			(3
				(if (!= (glory4_sav open: 1) 0)
					(Message msgGET 52 2 0 13 1 (bogus5 data?))
					(bogus4 format: (bogus5 data?) (glory4_sav name?))
					(theGame setCursor: normalCursor)
					(switch
						(= check1
							(Print
								addText: bogus4
								classButton: MyButton
								font: 999
								addButton: 1 2 0 14 1 25 45
								addButton: 2 2 0 15 1 105 45
								init:
							)
						)
						(1 (self changeState: 2))
						(2
							(glory4_sav close:)
							(= cycles 1)
						)
					)
				else
					(= cycles 1)
				)
			)
			(4
				(if (glory4_sav open: 2)
					(glory4_sav close:)
					(= seconds 2)
				else
					(Message msgGET 52 2 0 2 1 (bogus5 data?))
					(bogus4 format: (bogus5 data?) (glory4_sav name?))
					(Print addText: bogus4 init:)
					(self changeState: 8)
				)
			)
			(5
				(if (not (glory4_sav open: 0))
					(self changeState: 8)
					(return)
				)
				(= temp0 0)
				(while (< temp0 42)
					(= [codedStats temp0] [egoStats temp0])
					(++ temp0)
				)
				(= temp1
					(+ ((inventory at: 0) amount?) (/ global154 100))
				)
				(= svCharType heroType)
				(= svHighCrown (/ temp1 100))
				(= svLowCrown (mod temp1 100))
				(= svMiscEquip 0)
				(if (Btst 387) (= svMiscEquip (| svMiscEquip $0010)))
				(if (ego has: 34)
					(= svMiscEquip (| svMiscEquip $0020))
				)
				(= checkSum2 checkSum1)
				(= temp0 0)
				(while (< temp0 52)
					(= [statsKey (+ temp0 1)] [statsKey (+ temp0 1)])
					(= checkSum2 (+ checkSum2 [statsKey (+ temp0 1)]))
					(= temp0 (+ temp0 2))
				)
				(= bogus2 0)
				(= temp0 1)
				(while (< temp0 52)
					(= [statsKey (+ temp0 1)] [statsKey (+ temp0 1)])
					(= bogus2 (+ bogus2 [statsKey (+ temp0 1)]))
					(= temp0 (+ temp0 2))
				)
				(= svOil checkSum2)
				(= svHealingPotion bogus2)
				(= temp0 0)
				(while (< temp0 60)
					(= [statsKey (+ temp0 1)] [statsKey (+ temp0 1)])
					(= [statsKey (+ temp0 1)]
						(^ [statsKey (+ temp0 1)] [statsKey temp0])
					)
					(++ temp0)
				)
				((= temp2 (Str newWith: 1 {*})) at: 0 10)
				(glory4_sav writeString: { glory4.sav_})
				(glory4_sav writeString: temp2)
				(glory4_sav writeString: userName)
				(glory4_sav writeString: temp2)
				(= temp0 1)
				(while (< temp0 61)
					(bogus4 format: {%2x} (/ [statsKey temp0] 100))
					(glory4_sav writeString: bogus4)
					(bogus4 format: {%2x} (mod [statsKey temp0] 100))
					(glory4_sav writeString: bogus4)
					(++ temp0)
				)
				(glory4_sav writeString: temp2)
				(glory4_sav close:)
				(= seconds 2)
			)
			(6
				(= register 1)
				(messager say: 2 0 1 0 self)
			)
			(7
				(= checkSumKey 1)
				(self changeState: 9)
			)
			(8
				(User canInput: 1)
				(if
					(= temp4
						(Print
							addText: 2 0 16 1
							font: 999
							addButton: 1 2 0 14 1 55 30
							addButton: 2 2 0 15 1 95 30
							init:
						)
					)
					(switch temp4
						(1
							(User canInput: 0)
							(= temp4 9)
						)
						(2
							(User canInput: 0)
							(= temp4 2)
							(= svRations 25)
							(= svDaggers 190)
							(= svManaPotion 55)
							(= svPoisonCurePotion 109)
							(= bogus0 196)
							(= bogus1 242)
						)
					)
				)
				(self changeState: temp4)
			)
			(9 (messager say: 2 0 7 0 self))
			(10 (curRoom newRoom: 160))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_RETURN)
				register
			)
			(= register 0)
			(= cycles 1)
			(event claimed: 1)
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
