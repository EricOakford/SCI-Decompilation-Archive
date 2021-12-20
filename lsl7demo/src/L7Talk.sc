;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64040)
(include sci.sh)
(use Main)
(use DialogPlane)
(use TranslucentPlane)
(use ModalPlane)
(use PushButton)
(use GenDialog)
(use Plane)
(use String)
(use Feature)
(use System)

(public
	proc64040_0 0
	proc64040_1 1
	L7TalkWindow 2
)

(local
	local0
	local1
	local2
	theL7TalkWindow
)
(procedure (proc64040_0 &tmp temp0 temp1 temp2)
	(asm
		_line_   42
		_file_   {filename}
		_line_   44
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global246
		_line_   45
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global247
		_line_   46
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global248
		_line_   47
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global249
		_line_   48
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global250
		_line_   49
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global251
		_line_   50
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global252
		_line_   51
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global253
		_line_   52
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global254
		_line_   53
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global255
		_line_   54
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global256
		_line_   55
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global257
		_line_   56
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global258
		_line_   57
		pushi    #new
		pushi    0
		class    Set
		send     4
		sag      global259
		_line_   59
		pushi    #add
		pushi    2
		pushi    102
		pushi    105
		lag      global247
		send     8
		_line_   60
		pushi    #add
		pushi    2
		pushi    103
		pushi    75
		lag      global248
		send     8
		_line_   64
		pushi    #add
		pushi    5
		pushi    203
		pushi    196
		pushi    197
		pushi    193
		pushi    194
		lag      global251
		send     14
		_line_   65
		pushi    #add
		pushi    3
		pushi    192
		pushi    238
		pushi    223
		lag      global252
		send     10
		_line_   66
		pushi    #add
		pushi    2
		pushi    162
		pushi    166
		lag      global253
		send     8
		_line_   72
		pushi    #add
		pushi    5
		pushi    190
		pushi    188
		pushi    118
		pushi    122
		pushi    219
		lag      global259
		send     14
		_line_   75
		pushi    #new
		pushi    0
		class    List
		send     4
		sal      local1
		_line_   76
		pushi    #new
		pushi    0
		class    List
		send     4
		sal      local2
		_line_   77
		ldi      0
		sal      local0
		_line_   78
		ldi      0
		sat      temp0
code_01ce:
		lst      temp0
		ldi      250
		lt?     
		bnt      code_0235
		_line_   79
		ldi      1
		sat      temp1
		_line_   80
code_01e0:
		ldi      1
		bnt      code_0231
		_line_   81
		pushi    6
		pushi    0
		lst      temp0
		pushi    0
		lst      temp1
		pushi    12
		pushi    1
		calle    MakeMessageText,  12
		sat      temp2
		_line_   82
		bnt      code_0227
		_line_   83
		pushi    #addToEnd
		pushi    1
		push    
		lal      local1
		send     6
		_line_   84
		pushi    #addToEnd
		pushi    1
		lst      temp0
		lal      local2
		send     6
		_line_   85
		+at      temp1
		_line_   86
		+al      local0
		jmp      code_01e0
code_0227:
		_line_   87
		_line_   88
		jmp      code_0231
		jmp      code_01e0
code_0231:
		+at      temp0
		jmp      code_01ce
code_0235:
		_line_   93
		ret     
	)
)

(procedure (proc64040_1 param1 &tmp temp0 temp1)
	(if
	(or (== (= temp1 (Abs param1)) 70) (== temp1 112))
		(return 1)
	)
	(return
		(if (== (local2 indexOf: temp1) -1)
			(return 0)
		else
			(return 1)
		)
	)
)

(instance oTalkClose of PushButton
	(properties
		view -5236
	)
	
	(method (doSelect)
		(if (and theL7TalkWindow (theL7TalkWindow oPlane?))
			(theL7TalkWindow dispose:)
		else
			(MonoOut
				{oTalkClose selected with no current talk window}
			)
		)
	)
)

(class L7TalkWindow of Obj
	(properties
		scratch 0
		nCorner 1
		oPlane 0
		oTopics 0
		oClient 0
		nHeaderFont 0
		nButtonFont 0
	)
	
	(method (init theOClient theOTopics)
		(if
			(or
				(< argc 2)
				(not theOClient)
				(not theOTopics)
				(not (theOTopics size:))
			)
			(MonoOut {Illegal call of L7TalkWindow init})
			(return)
		)
		(super init: &rest)
		(= oClient theOClient)
		(= oTopics theOTopics)
		(self rebuild:)
		(= theL7TalkWindow self)
	)
	
	(method (dispose)
		(if oPlane (oPlane dispose:) (= oPlane 0))
		(= theL7TalkWindow (= oClient (= oTopics 0)))
		(super dispose: &rest)
	)
	
	(method (addTopic param1 &tmp temp0 temp1)
		(if (not oTopics)
			(MonoOut {Illegal addTopic call in L7TalkWindow})
			(return)
		)
		(= temp1 0)
		(= temp0 0)
		(while (< temp0 argc)
			(if (not (proc64040_1 [param1 temp0])) (return))
			(if (== (oTopics indexOf: [param1 temp0]) -1)
				(oTopics addToEnd: [param1 temp0])
				(= temp1 1)
			)
			(++ temp0)
		)
		(if temp1 (self rebuild:))
	)
	
	(method (rebuild &tmp temp0 oTopicsSize oClientGetName temp3 temp4 temp5 temp6 temp7 temp8 temp9 newTextButton newTextItem temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 temp21)
		(if oPlane (oPlane dispose:))
		(= oPlane (Plane new:))
		(oPlane picture: -2 init: 0 0 1 1)
		(= oClientGetName (oClient getName:))
		(= temp5
			(+ (GetTextWidth oClientGetName nHeaderFont 0) 25)
		)
		(= temp4
			(+
				(= temp4 (GetTextWidth_2 oClientGetName nHeaderFont 0))
				6
			)
		)
		(= temp3 (MakeMessageText 0 12 0 1 13))
		(if
		(< temp5 (= temp12 (GetTextWidth temp3 nButtonFont 0)))
			(= temp5 temp12)
		)
		(= oTopicsSize (oTopics size:))
		(= temp21 0)
		(= temp0 0)
		(while (< temp0 oTopicsSize)
			(if (< (= temp13 (oTopics at: temp0)) 0)
			else
				(++ temp21)
				(= temp14 (MakeMessageText 0 temp13 0 1 12))
				(if
				(< temp5 (= temp12 (GetTextWidth temp14 nButtonFont 0)))
					(= temp5 temp12)
				)
				(temp14 dispose:)
			)
			(++ temp0)
		)
		(= temp5 (+ temp5 5))
		(= temp6 3)
		(= temp7 (+ temp4 3))
		(= temp0 0)
		(while (< temp0 oTopicsSize)
			(if (< (= temp13 (oTopics at: temp0)) 0)
			else
				(= temp19 (!= (oTopics indexOf: (- 0 temp13)) -1))
				(= temp14 (MakeMessageText 0 temp13 0 1 12))
				(= newTextButton (TextButton new:))
				(if temp19 (= temp20 85) else (= temp20 87))
				(newTextButton
					text: temp14
					font: nButtonFont
					fore: temp20
					back: 237
					skip: 255
					nMinWidth: temp5
					x: temp6
					y: temp7
					oSelectNotify: self
					nSelectValue: temp13
					init: oPlane
				)
				(= temp7 (+ temp7 (newTextButton nHeight?) 3))
			)
			(++ temp0)
		)
		((= newTextButton (TextButton new:))
			text: temp3
			font: nButtonFont
			fore: 87
			back: 237
			skip: 255
			nMinWidth: temp5
			x: temp6
			y: temp7
			oSelectNotify: self
			nSelectValue: 112
			init: oPlane
		)
		(= temp9 (+ (newTextButton nWidth?) 6))
		(= temp8
			(+
				temp4
				3
				(* (+ temp21 1) (+ (newTextButton nHeight?) 3))
			)
		)
		((= newTextItem (TextItem new:))
			text: oClientGetName
			font: nHeaderFont
			fore: 0
			back: 82
			skip: 255
			nMinWidth: temp9
			x: 0
			y: 0
			priority: 10
			fixPriority: 1
			init: oPlane
		)
		(switch nCorner
			(0
				(= temp15 0)
				(= temp16 0)
				(= temp17 (- temp9 1))
				(= temp18 (- temp8 1))
			)
			(1
				(= temp15 0)
				(= temp17 639)
				(= temp16 (- 639 (- temp9 1)))
				(= temp18 (- temp8 1))
			)
			(2
				(= temp18 479)
				(= temp16 0)
				(= temp15 (- 479 (- temp8 1)))
				(= temp17 (- temp9 1))
			)
			(3
				(= temp18 479)
				(= temp17 639)
				(= temp15 (- 479 (- temp8 1)))
				(= temp16 (- 639 (- temp9 1)))
			)
		)
		(oPlane setRect: temp16 temp15 temp17 temp18)
		(oPlane setPri: 30)
		((OpaqueFeature new:) init: oPlane)
		((oTalkClose new:)
			x: 2
			y: 2
			setPri: 20
			signal: 16417
			init: oPlane
		)
		((TransView new:)
			remapColor: 237
			x: 0
			y: 0
			setPri: 0
			scaleSignal: 1
			scaleX: (* temp9 32)
			scaleY: (* temp8 32)
			maxScale: 20480
			init: oPlane
		)
	)
	
	(method (doSelect param1 &tmp temp0 oClientGetName temp2 temp3 temp4 temp5 temp6 temp7)
		(if (== param1 112)
			(= temp0 (MakeMessageText 0 0 53 1 14))
			(= oClientGetName (oClient getName:))
			(= temp2 (Str format: temp0 oClientGetName))
			(proc64896_7 temp0)
			(proc64896_7 oClientGetName)
			(if
				(not
					(= temp3
						(proc64033_6
							temp2
							(MakeMessageText 0 0 17 1 14)
							(MakeMessageText 0 0 18 1 14)
							20
						)
					)
				)
				(return -1)
			)
			(temp3 lower:)
			(if (oClient handleTopic: temp3)
				(return (proc64896_7 temp3))
			)
			(= temp6 0)
			(while (< temp6 local0)
				((= temp7 (Str with: (local1 at: temp6))) lower:)
				(if (< (= temp5 (temp7 size:)) (temp3 size:))
					(= temp4 (temp3 left: temp5))
				else
					(= temp4 (Str with: temp3))
				)
				(if (== (temp7 weigh: temp4) 0)
					(MonoOut {Hit on '%s'} temp7)
					(proc64896_7 temp4)
					(proc64896_7 temp7)
					(= param1 (local2 at: temp6))
					(break)
				)
				(proc64896_7 temp4)
				(proc64896_7 temp7)
				(++ temp6)
			)
			(proc64896_7 temp3)
		)
		(if (== param1 112) (= param1 70))
		(if (!= param1 70) (self addTopic: (- 0 param1)))
		(return
			(CueObj
				state: 0
				cycles: 0
				client: oClient
				theVerb: param1
				changeState: 3
			)
		)
	)
)
