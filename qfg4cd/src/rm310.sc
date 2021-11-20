;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use OccCyc)
(use TextIcon)
(use DText)
(use String)
(use Print)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	local0
	local1
	local2
	theSHTextIcon
)
(procedure (localproc_1447 param1 &tmp temp0 temp1 newStr temp3 temp4 temp5)
	(= newStr (Str new:))
	(if
		(and
			(== param1 bPiePan)
			(or
				(ego has: 28)
				((inventory at: 28) chestAmout?)
				(Btst 179)
			)
		)
		(Message msgGET 310 28 6 78 1 (newStr data?))
		(Print addText: (newStr data?) init:)
		(newStr dispose:)
		(return)
	)
	(= temp3
		(/
			(= temp0
				(switch param1
					(bBag 50)
					(bCandy 5)
					(bFlask 100)
					(bGarlic 25)
					(bHandbroom 350)
					(bPiePan 250)
					(bRations 50)
				)
			)
			100
		)
	)
	(= temp4 ((inventory at: 0) amount?))
	(cond 
		((>= kopeks temp0)
			(= kopeks (- kopeks temp0))
			(ego
				get:
					(switch param1
						(bBag 51)
						(bCandy 8)
						(bFlask 15)
						(bGarlic 22)
						(bHandbroom 39)
						(bPiePan 28)
						(bRations 4)
					)
			)
			(newStr dispose:)
		)
		(
			(or
				(> temp4 temp3)
				(and
					(== temp4 temp3)
					(>= (- (+ kopeks (* temp4 100)) temp0) 0)
				)
			)
			(if
			(>= (= kopeks (- kopeks (- temp0 (* temp3 100)))) 0)
				((inventory at: 0) amount: (- temp4 temp3))
			else
				((inventory at: 0) amount: (- (- temp4 temp3) 1))
				(= kopeks (+ kopeks 100))
			)
			(ego
				get:
					(switch param1
						(bBag 51)
						(bCandy 8)
						(bFlask 15)
						(bGarlic 22)
						(bHandbroom 39)
						(bPiePan 28)
						(bRations 4)
					)
			)
			(newStr dispose:)
		)
		(else
			(Message msgGET 310 28 6 68 1 (newStr data?))
			(Print addText: (newStr data?) init:)
			(newStr dispose:)
		)
	)
	(if (/ kopeks 100)
		((inventory at: 0)
			amount: (+ ((inventory at: 0) amount?) (/ kopeks 100))
		)
		(= kopeks (mod kopeks 100))
	)
	(localproc_1706)
)

(procedure (localproc_1706 &tmp newStr newStr_2 newStr_3 temp3 printLargeAlp printXtheX printXtheY)
	(= newStr (Str new:))
	(= newStr_2 (Str new:))
	(= newStr_3 (Str new:))
	(= temp3 ((inventory at: 0) amount?))
	(= printLargeAlp 0)
	(if (or temp3 kopeks)
		(Message msgGET 310 0 0 82 1 (newStr data?))
		(Print addText: (newStr data?) 0 0)
		(= printLargeAlp (Print largeAlp?))
		(= printXtheX (Print xtheX?))
		(= printXtheY (Print xtheY?))
		(if temp3
			(Message msgGET 310 0 0 80 1 (newStr_2 data?))
			(newStr format: (newStr_2 data?) temp3)
			(Print largeAlp: 0 addText: (newStr data?) 0 9)
		)
		(if kopeks
			(Message msgGET 310 0 0 81 1 (newStr_3 data?))
			(newStr format: (newStr_3 data?) kopeks)
			(Print addText: (newStr data?) 0 (if temp3 18 else 9))
		)
	else
		(Message msgGET 310 0 0 83 1 (newStr data?))
		(Print addText: (newStr data?) 0 0)
		(= printLargeAlp (Print largeAlp?))
		(= printXtheX (Print xtheX?))
		(= printXtheY (Print xtheY?))
	)
	(Print
		largeAlp: printLargeAlp
		xtheX: printXtheX
		xtheY: printXtheY
		init:
	)
	(newStr dispose:)
	(newStr_2 dispose:)
	(newStr_3 dispose:)
)

(procedure (localproc_18c2 &tmp temp0)
	(while ((= temp0 ((user curEvent?) new:)) type?)
	)
)

(instance rm310 of GloryRm
	(properties
		picture 310
		west 260
		leftY 173
	)
	
	(method (init)
		(= local2 (mod (++ global417) 3))
		(theMusic number: 310 setLoop: -1 play:)
		(ego init: normalize: setScaler: Scaler 130 86 171 130)
		(ego posn: -15 172)
		(= local0 75)
		(= local1 173)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						61
						163
						61
						171
						0
						171
						0
						0
						319
						0
						319
						189
						208
						189
						0
						189
						0
						174
						61
						174
						61
						187
						207
						187
						207
						181
						170
						181
						202
						123
						264
						123
						264
						112
						164
						112
					yourself:
				)
		)
		(self
			addPoly:
				((Polygon new:)
					init: 113 124 164 124 159 161 116 167 76 149
					yourself:
				)
				130
		)
		(olga init: setPri: 53 setCycle: Fwd)
		(fire init: setCycle: Fwd)
		(catOnTable init: setPri: 179)
		(catByTable init: setPri: 185)
		(catByFire init:)
		(catMantelLick init:)
		(catMantelTail init:)
		(catOnTableButt init: setPri: 196)
		(catJumpLedge init: setPri: 75 setCycle: End)
		(fireLogs init: approachVerbs: 4)
		(hearth init: approachVerbs: 4)
		(garlic1 init: approachVerbs: 4)
		(garlic2 init: approachVerbs: 4)
		(garlic3 init: approachVerbs: 4)
		(garlic4 init: approachVerbs: 4)
		(garlic5 init: approachVerbs: 4)
		(garlic6 init: approachVerbs: 4)
		(clocks init:)
		(lamp init: approachVerbs: 4)
		(candelabra init: approachVerbs: 4)
		(plate1 init: approachVerbs: 4)
		(plate2 init: approachVerbs: 4)
		(temple init: approachVerbs: 4)
		(basketCat init: approachVerbs: 4)
		(basket init: approachVerbs: 4)
		(plates init: approachVerbs: 4)
		(frames init:)
		(smallPot init: approachVerbs: 4)
		(bluePot init:)
		(ewer1 init:)
		(brownPot1 init:)
		(brownPot2 init:)
		(ewer2 init: approachVerbs: 4)
		(barrel1 init: approachVerbs: 4)
		(barrel2 init: approachVerbs: 4)
		(buckets init: approachVerbs: 4)
		(counterPot init: approachVerbs: 4)
		(crates1 init: approachVerbs: 4)
		(crates2 init: approachVerbs: 4)
		(vines1 init: approachVerbs: 4)
		(vines2 init: approachVerbs: 4)
		(shopWindow init: approachVerbs: 4)
		(counter init: approachVerbs: 4)
		(super init: &rest)
		(heroTeller
			init:
				ego
				310
				32
				128
				(cond 
					((and (== global417 1) (not Night)) 29)
					(
					(and (== Day (+ global421 1)) (== global417 2)) 30)
					(else 31)
				)
		)
		(self setScript: sEnterScr)
	)
	
	(method (newRoom n)
		(theMusic fade: 0)
		(super newRoom: n &rest)
	)
	
	(method (leaveRoom)
		(++ global421)
		(cond 
			((and (== global417 1) (not Night)) (messager say: 25 6 39))
			(
			(and (== Day (+ global421 1)) (== global417 2)) (messager say: 25 6 40))
			(else (DailyMsg 25 6 41))
		)
		(super leaveRoom:)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo local0 local1 self)
			)
			(1
				(if (and (== global417 1) (not Night))
					(messager say: 28 6 35 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(cond 
					(
					(and (Btst 37) (not (Btst 103)) (not (Btst 48))) (Bset 103) (messager say: 25 6 2 0 self))
					((and (Btst 39) (not (Btst 104))) (Bset 104) (messager say: 25 6 3 0 self))
					((and (Btst 41) (not (Btst 105))) (Bset 105) (messager say: 25 6 4 0 self))
					((and (Btst 45) (not (Btst 106))) (Bset 106) (messager say: 25 6 5 0 self))
					((and (Btst 115) (not (Btst 107))) (Bset 107) (messager say: 25 6 6 0 self))
					((and (== global417 1) (not Night)) (messager say: 25 6 36 0 self))
					(
					(and (== Day (+ global421 1)) (== global417 2)) (messager say: 25 6 37 0 self))
					(else (DailyMsg 25 6 38 self))
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCatScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(switch (= temp0 (Random 0 2))
					(0
						(catMantelLick
							setCycle: OccCyc self 1 5 100 (Random 1 3) self
						)
					)
					(1
						(catMantelTail
							setCycle: OccCyc self 1 5 100 (Random 1 3) self
						)
					)
					(else 
						(catOnTableButt setCycle: End self)
					)
				)
			)
			(1 (= seconds (Random 6 30)))
			(2 (self changeState: 0))
		)
	)
)

(instance olgaTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 94 0))
	)
	
	(method (sayMessage &tmp temp0)
		(switch iconValue
			(42 (Bset 84))
			(47
				(Bset 85)
				(if (and (== stovichState 0) (Btst 86)) (= stovichState 1))
			)
			(16 (Bset 95))
			(19 (Bset 97))
			(21 (Bset 98))
			(22 (Bset 96))
			(24 (Bset 93))
			(25 (Bset 94))
			(27 (Bset 100))
			(44
				(self clean:)
				(= temp0
					(theGame
						setCursor: ((theIconBar getCursor:)
							view: 999
							loop: 0
							cel: 0
							yourself:
						)
					)
				)
				(Bset 51)
				(Bset 148)
				(cast eachElementDo: #perform (ScriptID 90 0) 1)
				(purchase init: show: dispose:)
				(Bclr 51)
				(theGame setCursor: temp0)
			)
		)
		(if (!= iconValue 44) (super sayMessage: &rest))
	)
	
	(method (showCases)
		(super
			showCases:
				42
				(not (Btst 84))
				46
				(Btst 84)
				47
				(if (== stovichState 0) (> global417 1) else 0)
				48
				(== stovichState 1)
				49
				(== stovichState 2)
				50
				(== stovichState 3)
				51
				(== stovichState 4)
				52
				(== stovichState 5)
				53
				(== stovichState 6)
				54
				(== stovichState 7)
				55
				(== stovichState 8)
				56
				(== stovichState 9)
				7
				(if (not (Btst 115)) (== local2 0) else 0)
				8
				(== local2 1)
				26
				(if (== local2 1) (not (Btst 41)) else 0)
				27
				(if (Btst 41) (not (Btst 100)) else 0)
				23
				(if (== local2 0) (not (Btst 45)) else 0)
				24
				(if (and (not (Btst 45)) (Btst 92))
					(not (Btst 93))
				else
					0
				)
				25
				(if (Btst 45) (not (Btst 94)) else 0)
				13
				(== local2 0)
				14
				(== local2 1)
				12
				(== local2 2)
				10
				(== local2 0)
				15
				(if (== local2 2) (not (Btst 43)) else 0)
				16
				(if (Btst 43) (not (Btst 95)) else 0)
				11
				(== local2 1)
				22
				(if (Btst 115) (not (Btst 96)) else 0)
				9
				(== local2 2)
				17
				(if (== local2 2) (not (Btst 37)) else 0)
				18
				(if (Btst 37) (not (Btst 48)) else 0)
				19
				(if (Btst 48) (not (Btst 97)) else 0)
				20
				(if (and (Btst 37) (not (Btst 39)))
					(not (Btst 48))
				else
					0
				)
				21
				(if (Btst 39) (not (Btst 98)) else 0)
		)
	)
	
	(method (clean)
		(super clean:)
		((ScriptID 94 1) dispose:)
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (sayMessage &tmp temp0)
		(cond 
			((OneOf iconValue 48 50 52 54) (ego addHonor: 50) (++ stovichState))
			((== iconValue 42) (Bset 84))
			((== iconValue 58)
				(self clean:)
				(if (not (Btst 148))
					(Bset 148)
					(cast eachElementDo: #perform (ScriptID 90 0) 1)
				)
				(= temp0
					(theGame
						setCursor: ((theIconBar getCursor:)
							view: 999
							loop: 0
							cel: 0
							yourself:
						)
					)
				)
				(Bset 51)
				(Bset 148)
				(purchase init: show: dispose:)
				(Bclr 51)
				(theGame setCursor: temp0)
				(return 1)
			)
		)
		(return (super sayMessage: &rest))
	)
	
	(method (showCases)
		(super
			showCases:
				42
				(not (Btst 84))
				48
				(== stovichState 1)
				50
				(== stovichState 3)
				52
				(== stovichState 5)
				54
				(== stovichState 7)
		)
	)
)

(instance purchase of PuzzleBar
	(properties)
	
	(method (init &tmp temp0)
		(Bclr 6)
		(FrameOut)
		(UpdatePlane
			((curRoom plane?) back: 0 picture: -1 yourself:)
		)
		((ScriptID 0 21) doit: 100)
		(super init: &rest)
		(buyIcon x: 139 y: 12 priority: 100 fixPriority: 1)
		((ScriptID 34 3) x: 92 y: 12)
		((ScriptID 34 2) x: 45 y: 12)
		(self
			add:
				buyIcon
				bLeft
				bRight
				bBag
				bCandy
				bFlask
				bGarlic
				bHandbroom
				bPiePan
				bRations
		)
	)
	
	(method (dispose)
		(self changeCursor: 943)
		((curRoom plane?) drawPic: (curRoom picture?))
		(Bclr 51)
		(Bclr 147)
		(theIconBar curIcon: (theIconBar at: 3))
		(Bclr 148)
		(cast eachElementDo: #perform (ScriptID 90 0) 0)
		((ScriptID 0 21) doit: curRoomNum)
		(Bset 6)
		(super dispose: &rest)
	)
	
	(method (show &tmp [temp0 2])
		(= curIcon 0)
		(= state (| state $0020))
		(self eachElementDo: #init self)
		(UpdatePlane plane)
		(cond 
			((not curIcon) 0)
			((HaveMouse) (self changeCursor: theCursor))
			(else
				(self
					changeCursor: theCursor
					changeCursor:
						(+
							(curIcon nsLeft?)
							(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
						)
						(- (curIcon nsBottom?) 3)
				)
			)
		)
		(localproc_18c2)
		(self resetPuzzle:)
		(SetNowSeen buyIcon)
		(SetNowSeen (ScriptID 34 3))
		(SetNowSeen (ScriptID 34 2))
		(self doit: hide:)
	)
	
	(method (resetPuzzle)
		(= theSHTextIcon 0)
	)
	
	(method (helpYou &tmp newStr)
		(= newStr (Str new:))
		(Message msgGET 310 28 6 77 1 (newStr data?))
		(Print addText: (newStr data?) init:)
		(newStr dispose:)
		(localproc_1706)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane
			bitmap: 0
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 40 30 280 170
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
		(self add: (ScriptID 34 2) (ScriptID 34 3))
	)
)

(class SHTextIcon of TextIcon
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
		state $0000
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
		view 905
		loop 0
		cel 2
		bitmap 0
		yStep 2
		signal $0081
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
		type $4000
		message -1
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 7
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		owner 0
		value 0
		value2 0
		value3 0
		font 999
		text 0
		textColor 50
		textType 0
		downClick 0
		upClick 0
		upClickOff 0
	)
	
	(method (init)
		(super init: purchase)
		(= x nsLeft)
		(= y nsTop)
		(if text
			(= value
				((myDText new:)
					posn: (+ nsLeft 21) (- nsTop 5)
					text: (text data?)
					font: 999
					fore: 0
					back: 254
					skip: 254
					setSize:
					setPri: 254
					init: (owner puzzleCast?)
					yourself:
				)
			)
		)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if
				(and
					(< (- nsLeft 10) theObjOrX)
					(< theObjOrX (+ nsRight 45))
					(< (- nsTop 5) theY)
				)
				(< theY (- nsBottom 13))
			else
				0
			)
		)
	)
	
	(method (select)
		(if (!= theSHTextIcon self)
			(if theSHTextIcon (theSHTextIcon highlight: 0 1))
			(= theSHTextIcon self)
			(self highlight: 1)
		else
			(= theSHTextIcon 0)
		)
	)
	
	(method (highlight param1)
		(value dispose:)
		(= value
			((myDText new:)
				posn: (+ nsLeft 21) (- nsTop 5)
				text: (text data?)
				font: 999
				fore:
					(cond 
						((and (== theSHTextIcon self) (<= argc 1)) 106)
						(param1 43)
						(else 0)
					)
				back: 254
				skip: 254
				setSize:
				setPri: 255
				init: (owner puzzleCast?)
				yourself:
			)
		)
		(UpdateScreenItem self)
		(FrameOut)
	)
)

(instance bLeft of SHTextIcon
	(properties
		nsLeft 19
		nsTop 34
		view 311
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance bRight of SHTextIcon
	(properties
		nsLeft 118
		nsTop 34
		view 311
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance bBag of SHTextIcon
	(properties
		nsLeft 133
		nsTop 69
		loop 2
		cel 0
		value 50
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 67 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance bCandy of SHTextIcon
	(properties
		nsLeft 36
		nsTop 56
		loop 8
		cel 4
		value 5
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 61 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance bFlask of SHTextIcon
	(properties
		nsLeft 35
		nsTop 77
		cel 9
		value 100
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 62 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance bGarlic of SHTextIcon
	(properties
		nsLeft 35
		nsTop 40
		loop 2
		cel 1
		value 25
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 63 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance bHandbroom of SHTextIcon
	(properties
		nsLeft 133
		nsTop 45
		loop 10
		cel 0
		value 350
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 64 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance bPiePan of SHTextIcon
	(properties
		nsLeft 37
		nsTop 99
		loop 2
		cel 10
		value 250
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 65 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance bRations of SHTextIcon
	(properties
		nsLeft 133
		nsTop 93
		cel 8
		value 50
	)
	
	(method (init)
		(= text (Str new:))
		(Message msgGET 310 33 141 66 1 (text data?))
		(super init: &rest)
	)
	
	(method (dispose)
		(text dispose:)
		(super dispose:)
	)
)

(instance buyIcon of TextIcon
	(properties
		nsLeft 60
		nsTop 20
		view 936
		loop 3
	)
	
	(method (select)
		(if (super select: &rest)
			(if theSHTextIcon
				(theSHTextIcon show:)
				(localproc_1447 theSHTextIcon)
				(theSHTextIcon highlight: 0 1)
				(= theSHTextIcon 0)
			else
				(messager say: 28 6 79)
			)
		)
	)
)

(instance myDText of DText
	(properties)
	
	(method (dispose &tmp planeCasts theBitmap)
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(cast delete: self)
		(if (self isNotHidden:) (DeleteScreenItem self))
		((= planeCasts (plane casts?))
			eachElementDo: #delete self
		)
		(= plane 0)
		(DisposeClone self)
		(if theBitmap (Bitmap 1 theBitmap))
	)
)

(instance olga of Prop
	(properties
		noun 25
		x 134
		y 116
		priority 64
		fixPriority 1
		view 315
		signal $4001
		cycleSpeed 20
	)
	
	(method (init)
		(super init: &rest)
		(olgaTeller
			init:
				olga
				310
				32
				141
				(cond 
					((and (== global417 1) (not Night)) 29)
					(
					(and (== Day (+ global421 1)) (== global417 2)) 30)
					(else 31)
				)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(15
				(= temp0
					(theGame
						setCursor: ((theIconBar getCursor:)
							view: 999
							loop: 0
							cel: 0
							yourself:
						)
					)
				)
				(Bset 51)
				(Bset 148)
				(cast eachElementDo: #perform (ScriptID 90 0) 1)
				(purchase init: show: dispose:)
				(Bclr 51)
				(theGame setCursor: temp0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fire of Prop
	(properties
		noun 1
		x 189
		y 107
		fixPriority 1
		view 310
		signal $4001
		cycleSpeed 12
		detailLevel 3
	)
)

(instance catMantelLick of Prop
	(properties
		noun 34
		x 220
		y 55
		view 310
		loop 5
	)
)

(instance catJumpLedge of Prop
	(properties
		noun 34
		x 114
		y 137
		view 310
		loop 3
		signal $4001
	)
)

(instance catMantelTail of Prop
	(properties
		noun 34
		x 220
		y 55
		view 310
		loop 4
		signal $4001
		detailLevel 3
	)
)

(instance catOnTableButt of Prop
	(properties
		noun 34
		x 217
		y 124
		view 310
		loop 2
		cel 1
		signal $4001
		detailLevel 3
	)
	
	(method (setCycle)
		(= cel 1)
		(super setCycle: &rest)
	)
)

(instance catOnTable of View
	(properties
		noun 34
		x 217
		y 124
		view 310
		loop 2
		signal $4000
	)
)

(instance catByTable of View
	(properties
		noun 34
		x 214
		y 163
		view 310
		loop 1
		signal $4001
	)
)

(instance catByFire of View
	(properties
		noun 34
		x 132
		y 102
		priority 1
		fixPriority 1
		view 310
		loop 1
		cel 2
		signal $4000
	)
)

(instance catOnFire of View
	(properties
		x 217
		y 124
		fixPriority 1
		view 310
		loop 1
		cel 3
	)
)

(instance catOnShelf of View
	(properties
		x 259
		y 69
		fixPriority 1
		view 310
		loop 1
		cel 1
	)
)

(instance fireLogs of Feature
	(properties
		noun 1
		nsLeft 169
		nsTop 90
		nsRight 209
		nsBottom 102
		sightAngle 180
		x 189
		y 96
	)
)

(instance hearth of Feature
	(properties
		noun 2
		nsLeft 156
		nsTop 67
		nsRight 223
		nsBottom 106
		sightAngle 180
		x 189
		y 86
	)
)

(instance garlic1 of Feature
	(properties
		noun 3
		nsLeft 48
		nsTop 12
		nsRight 83
		nsBottom 38
		sightAngle 180
		x 65
		y 25
	)
)

(instance garlic2 of Feature
	(properties
		noun 3
		nsLeft 82
		nsTop 25
		nsRight 113
		nsBottom 45
		sightAngle 180
		x 97
		y 35
	)
)

(instance garlic3 of Feature
	(properties
		noun 3
		nsLeft 232
		nsTop -1
		nsRight 270
		nsBottom 48
		sightAngle 180
		x 251
		y 23
	)
)

(instance garlic4 of Feature
	(properties
		noun 3
		nsLeft 270
		nsTop 22
		nsRight 318
		nsBottom 55
		sightAngle 180
		x 294
		y 38
	)
)

(instance garlic5 of Feature
	(properties
		noun 3
		nsLeft 250
		nsTop 65
		nsRight 270
		nsBottom 73
		sightAngle 180
		x 260
		y 69
	)
)

(instance garlic6 of Feature
	(properties
		noun 3
		nsLeft 265
		nsTop 55
		nsRight 277
		nsBottom 65
		sightAngle 180
		x 271
		y 60
	)
)

(instance clocks of Feature
	(properties
		noun 4
		nsLeft 271
		nsTop 63
		nsRight 287
		nsBottom 117
		sightAngle 180
		x 279
		y 90
	)
)

(instance lamp of Feature
	(properties
		noun 5
		nsLeft 286
		nsTop 54
		nsRight 301
		nsBottom 75
		sightAngle 180
		x 293
		y 64
	)
)

(instance candelabra of Feature
	(properties
		noun 6
		nsLeft 147
		nsTop 37
		nsRight 158
		nsBottom 52
		sightAngle 180
		x 152
		y 44
	)
)

(instance plate1 of Feature
	(properties
		noun 7
		nsLeft 160
		nsTop 39
		nsRight 174
		nsBottom 52
		sightAngle 180
		x 167
		y 45
	)
)

(instance plate2 of Feature
	(properties
		noun 7
		nsLeft 199
		nsTop 37
		nsRight 216
		nsBottom 51
		sightAngle 180
		x 207
		y 44
	)
)

(instance temple of Feature
	(properties
		noun 8
		nsLeft 176
		nsTop 34
		nsRight 198
		nsBottom 51
		sightAngle 180
		x 187
		y 42
	)
)

(instance basketCat of Feature
	(properties
		noun 9
		nsLeft 20
		nsTop 98
		nsRight 57
		nsBottom 112
		sightAngle 180
		x 38
		y 105
	)
)

(instance basket of Feature
	(properties
		noun 10
		nsLeft 26
		nsTop 91
		nsRight 55
		nsBottom 121
		sightAngle 180
		x 40
		y 106
	)
)

(instance plates of Feature
	(properties
		noun 11
		nsLeft 61
		nsTop 1
		nsRight 118
		nsBottom 34
		sightAngle 180
		x 89
		y 17
	)
)

(instance frames of Feature
	(properties
		noun 12
		nsLeft 288
		nsTop 79
		nsRight 319
		nsBottom 92
		sightAngle 180
		x 303
		y 85
	)
)

(instance smallPot of Feature
	(properties
		noun 13
		nsLeft 300
		nsTop 53
		nsRight 314
		nsBottom 72
		sightAngle 180
		x 307
		y 62
	)
)

(instance bluePot of Feature
	(properties
		noun 14
		nsLeft 263
		nsTop 133
		nsRight 319
		nsBottom 178
		sightAngle 180
		x 291
		y 155
	)
)

(instance ewer1 of Feature
	(properties
		noun 16
		nsLeft 286
		nsTop 88
		nsRight 311
		nsBottom 113
		sightAngle 180
		x 298
		y 100
	)
)

(instance brownPot1 of Feature
	(properties
		noun 15
		nsLeft 279
		nsTop 117
		nsRight 319
		nsBottom 133
		sightAngle 180
		x 299
		y 125
	)
)

(instance brownPot2 of Feature
	(properties
		noun 15
		nsLeft 296
		nsTop 102
		nsRight 319
		nsBottom 118
		sightAngle 180
		x 307
		y 110
	)
)

(instance ewer2 of Feature
	(properties
		noun 16
		nsLeft 15
		nsTop 150
		nsRight 53
		nsBottom 182
		sightAngle 180
		x 34
		y 166
	)
)

(instance barrel1 of Feature
	(properties
		noun 17
		nsLeft 43
		nsTop 127
		nsRight 68
		nsBottom 148
		sightAngle 180
		x 55
		y 137
	)
)

(instance barrel2 of Feature
	(properties
		noun 18
		nsLeft 10
		nsTop 138
		nsRight 45
		nsBottom 151
		sightAngle 180
		x 27
		y 144
	)
)

(instance buckets of Feature
	(properties
		noun 19
		nsLeft 42
		nsTop 147
		nsRight 80
		nsBottom 170
		sightAngle 180
		x 61
		y 158
	)
)

(instance counterPot of Feature
	(properties
		noun 20
		nsLeft 62
		nsTop 97
		nsRight 78
		nsBottom 112
		sightAngle 180
		x 70
		y 104
	)
)

(instance crates1 of Feature
	(properties
		noun 21
		nsLeft 66
		nsTop 120
		nsRight 97
		nsBottom 145
		sightAngle 180
		x 81
		y 132
		z 10
	)
)

(instance crates2 of Feature
	(properties
		noun 21
		nsLeft 91
		nsTop 115
		nsRight 122
		nsBottom 138
		sightAngle 180
		x 106
		y 126
		z 15
	)
)

(instance vines1 of Feature
	(properties
		noun 22
		nsLeft 13
		nsTop 22
		nsRight 42
		nsBottom 82
		sightAngle 180
		x 27
		y 52
	)
)

(instance vines2 of Feature
	(properties
		noun 22
		nsLeft 78
		nsTop 43
		nsRight 104
		nsBottom 85
		sightAngle 180
		x 91
		y 64
	)
)

(instance shopWindow of Feature
	(properties
		noun 23
		nsLeft 12
		nsTop 19
		nsRight 107
		nsBottom 101
		sightAngle 180
		x 59
		y 60
	)
)

(instance counter of Feature
	(properties
		noun 24
		nsLeft 197
		nsTop 110
		nsRight 256
		nsBottom 168
		sightAngle 180
		x 226
		y 139
	)
)
