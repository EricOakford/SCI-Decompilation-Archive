;;; Sierra Script 1.0 - (do not remove this comment)
(script# 810)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use MoveFwd)
(use Motion)
(use Actor)
(use System)

(public
	rm810 0
	proc810_1 1
	proc810_2 2
	beam 3
)

(local
	local0
	[local1 20] = [0 189 0 -10 319 -10 319 189 254 189 222 132 126 132 126 137 179 137 142 189]
	[local21 16] = [0 189 0 -10 319 -10 319 189 256 189 207 106 199 106 142 189]
	[local37 16] = [0 189 0 -10 319 -10 319 189 254 189 222 132 183 132 142 189]
	local53
	local54
	local55
)
(procedure (proc810_1)
	(curRoom drawPic: (curRoom picture?))
	(endHallway init: addToPic:)
	(ego init: show:)
	(beam cel: 0 show:)
	(chink show:)
	(localproc_0709)
)

(procedure (proc810_2)
	(asm
		ldi      180
		eq?     
		bnt      code_02bb
		pushi    #posn
		pushi    2
		pushi    200
		pushi    133
		lag      ego
		send     8
		+al      local0
		jmp      code_02d0
code_02bb:
		pushi    #posn
		pushi    2
		pushi    200
		pushi    188
		pushi    264
		pushi    1
		pushi    0
		lag      ego
		send     14
		-al      local0
code_02d0:
		ipToa    20
		; WARNING: Can't determine property name for index 20
		lsl      local0
		ldi      3
		eq?     
		aTop     36
		; WARNING: Can't determine property name for index 36
		bnt      code_02f3
		dpToa    20
		; WARNING: Can't determine property name for index 20
		pushi    #posn
		pushi    2
		pushi    156
		pushi    135
		pushi    102
		pushi    0
		pushi    423
		pushi    1
		pushi    0
		lag      ego
		send     18
code_02f3:
		pushi    0
		call     localproc_0709,  0
		pushi    #hide
		pushi    0
		lofsa    chink
		send     4
		pushi    #hide
		pushi    0
		lofsa    beam
		send     4
		pushi    #startUpd
		pushi    0
		pushi    102
		pushi    0
		lofsa    secretDoor
		send     8
		pushi    #drawPic
		pushi    2
		pushi    810
		pushi    10
		lag      curRoom
		send     8
		pushi    #addToPic
		pushi    0
		lofsa    endHallway
		send     4
		lsl      local0
		dup     
		ldi      2
		eq?     
		bnt      code_0346
		pushi    #show
		pushi    0
		lofsa    chink
		send     4
		pushi    #show
		pushi    0
		lofsa    beam
		send     4
		jmp      code_0359
code_0346:
		dup     
		ldi      1
		eq?     
		bnt      code_0359
		pushi    #show
		pushi    0
		pushi    301
		pushi    0
		lofsa    secretDoor
		send     8
code_0359:
		toss    
		pushi    #doit
		pushi    0
		pushi    #scaler
		pushi    0
		lag      ego
		send     4
		send     4
		ldi      10
		aTop     26
		; WARNING: Can't determine property name for index 26
		jmp      code_03ea
		dup     
		ldi      3
		eq?     
		bnt      code_0392
		pushi    #show
		pushi    0
		pushi    423
		pushi    1
		pushi    1
		pushi    300
		pushi    4
		class    MoveTo
		push    
		pushi    184
		pushi    135
		pushSelf
		lag      ego
		send     22
		jmp      code_03ea
code_0392:
		dup     
		ldi      4
		eq?     
		bnt      code_039e
		ldi      30
		aTop     32
		; WARNING: Can't determine property name for index 32
		jmp      code_03ea
code_039e:
		dup     
		ldi      5
		eq?     
		bnt      code_03d7
		lsl      local0
		ldi      2
		eq?     
		bnt      code_03d1
		pushi    #tstFlag
		pushi    2
		pushi    711
		pushi    16
		pushi    2
		pushi    80
		pushi    0
		callk    ScriptID,  4
		send     8
		not     
		bnt      code_03d1
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    4
		pushi    0
		pushSelf
		lag      messager
		send     14
		jmp      code_03ea
code_03d1:
		ldi      2
		aTop     26
		; WARNING: Can't determine property name for index 26
		jmp      code_03ea
code_03d7:
		dup     
		ldi      6
		eq?     
		bnt      code_03ea
		pushi    #handsOn
		pushi    0
		lag      theGame
		send     4
		pushi    #dispose
		pushi    0
		self     4
code_03ea:
		toss    
		ret     
	)
)

(procedure (localproc_0709)
	(if (curRoom obstacles?)
		((curRoom obstacles?) dispose:)
	)
	(switch local0
		(1
			(curRoom
				addObstacle: (mainPoly points: @local37 size: 8 yourself:)
			)
		)
		(2
			(curRoom
				addObstacle: (mainPoly points: @local21 size: 8 yourself:)
			)
		)
		(else 
			(curRoom
				addObstacle: (mainPoly points: @local1 size: 10 yourself:)
			)
		)
	)
)

(instance rm810 of CastleRoom
	(properties
		noun 3
		picture 810
		style $000a
		horizon 129
		walkOffEdge 1
	)
	
	(method (init)
		(switch prevRoomNum
			(781
				(= local0 1)
				(= local53 1)
				(theMusic fadeTo: 810 -1)
			)
			(else 
				(ego posn: 200 186)
				(= local0 3)
			)
		)
		(localproc_0709)
		(super init: &rest)
		(walls init:)
		(ego init: reset:)
		(if (== prevRoomNum 800) (ego loop: 9 cel: 3))
		(ego setScale: Scaler 100 70 190 129)
		((ego scaler?) doit:)
		(secretDoor init: hide:)
		(switch prevRoomNum
			(781
				(secretDoor cel: 3 show: stopUpd:)
				(ego
					normal: 0
					view: 781
					setScale: 0
					scaleX: 128
					scaleY: 128
					loop: 3
					cel: 9
					posn: 193 134
				)
				(self setScript: enterBedroom)
			)
			(else  (ego posn: 200 186))
		)
		(chink init: hide:)
		(beam init: hide:)
		(endHallway addToPic:)
		(if (not script) (theGame handsOn:))
	)
	
	(method (doit &tmp temp0 egoEdgeHit)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000)
				(if (!= local0 1)
					(self setScript: changeHallways 0 270)
				)
			)
			((= egoEdgeHit (ego edgeHit?))
				(switch egoEdgeHit
					(3
						(if (== local0 3)
							(curRoom newRoom: 800)
						else
							(self setScript: changeHallways 0 180)
						)
					)
					(1
						(self setScript: changeHallways 0 0)
					)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 951)
		(DisposeScript 969)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance changeHallways of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: register self)
			)
			(1
				(secretDoor startUpd:)
				(if register
					(ego setMotion: MoveFwd 15 self)
				else
					(= cycles 1)
				)
			)
			(2
				(beam setScript: 0)
				(if (== (ego heading?) 180)
					(ego posn: 200 133)
					(++ local0)
				else
					(ego posn: 200 188 setHeading: 0)
					(-- local0)
				)
				(++ state)
				(if (= register (== local0 3))
					(-- state)
					(ego posn: 156 135 hide: normal: 0)
				)
				(localproc_0709)
				(chink hide:)
				(beam hide:)
				(secretDoor startUpd: hide:)
				(curRoom drawPic: 810 10)
				(endHallway addToPic:)
				(switch local0
					(2 (chink show:) (beam show:))
					(1 (secretDoor show: stopUpd:))
				)
				((ego scaler?) doit:)
				(= cycles 10)
			)
			(3
				(ego show: normal: 1 setMotion: MoveTo 184 135 self)
			)
			(4 (= ticks 30))
			(5
				(if
					(and
						(== local0 2)
						(not ((ScriptID 80 0) tstFlag: 711 16))
					)
					(messager say: 1 0 4 0 self)
				else
					(= cycles 2)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterBedroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local53
					(secretDoor cel: 3)
					(= cycles 1)
				else
					(secretDoor setCycle: EndLoop self)
				)
			)
			(1
				(ego
					normal: 0
					view: 781
					setScale: 0
					scaleX: 128
					scaleY: 128
					loop: 3
					cycleSpeed: 8
					posn: 193 134
				)
				(if local53
					(++ state)
					(ego cel: 9 setCycle: BegLoop self)
				else
					(ego cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(if (not ((ScriptID 80 0) tstFlag: 711 1024))
					((ScriptID 80 0) setFlag: 711 1024)
					(messager say: 6 5 13 0 self)
				else
					(= cycles 1)
				)
			)
			(3
				(if local53
					(ego
						posn: (secretDoor approachX?) (secretDoor approachY?)
						oldScaleSignal: 0
						setScale: Scaler 100 70 190 129
						reset: 1
					)
					((ego scaler?) doit:)
					(= local53 0)
					(secretDoor setCycle: BegLoop self)
				else
					(curRoom newRoom: 781)
				)
			)
			(4
				(secretDoor stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance walls of Feature
	(properties
		noun 5
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1
				(if local54 (= temp0 11) else (= temp0 12))
				(messager say: noun theVerb temp0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(= local54
			(if (InRect 142 64 157 156 event) (== local0 2) else 0)
		)
		(return (not (& $0002 (OnControl 4 (event x?) (event y?)))))
	)
)

(instance endHallway of View
	(properties
		onMeCheck $0000
		view 810
	)
	
	(method (init)
		(= cel (if (== local0 3) 0 else local0))
		(switch cel
			(1 (= x 203) (= y 101))
			(2 (= x 200) (= y 106))
		)
		(= priority 0)
		(= signal (| signal $6010))
		(super init: &rest)
	)
)

(instance chink of View
	(properties
		x 148
		y 167
		z 42
		noun 4
		approachX 164
		approachY 169
		view 810
		loop 1
		priority 10
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 1 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: (ScriptID 811))
			)
			(5
				(curRoom setScript: (ScriptID 811))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beam of Prop
	(properties
		x 148
		y 167
		z 42
		onMeCheck $0000
		view 810
		loop 2
		cel 8
		signal $4010
	)
	
	(method (doit)
		(if (not (& signal $0008))
			(if
				(and
					(<= (ego x?) 216)
					(>= 169 (ego y?))
					(>= (ego y?) 165)
				)
				(= cel (- (/ (- (ego x?) x) 7) 1))
				(= priority (- (ego priority?) 1))
			else
				(= priority (CoordPri y))
				(= cel 8)
			)
		)
		(super doit:)
	)
	
	(method (setScript newScript)
		(super setScript: newScript &rest)
	)
)

(instance secretDoor of Prop
	(properties
		x 175
		y 127
		noun 6
		approachX 192
		approachY 134
		view 781
		loop 2
		signal $6010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: enterBedroom)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mainPoly of Polygon
	(properties
		type $0002
	)
)
