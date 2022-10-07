;;; Sierra Script 1.0 - (do not remove this comment)
(script# 840)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm840 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
)
(instance rm840 of CastleRoom
	(properties
		noun 9
		picture 840
		style $000a
		horizon 10
		north 720
		west 710
		vanishingX 202
		vanishingY 95
		minScaleSize 35
		minScaleY 111
		maxScaleY 173
	)
	
	(method (init)
		(= scalerCode guardScalerCode)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-260
						-10
						319
						-10
						319
						179
						266
						142
						272
						142
						252
						129
						246
						129
						220
						111
						147
						111
						147
						115
						171
						115
						137
						135
						85
						103
						65
						55
						31
						55
						31
						58
						58
						58
						80
						105
						134
						139
						77
						178
						-260
						178
					yourself:
				)
		)
		(features
			add: stairCase roomFeatures floors
			eachElementDo: #init
		)
		(super init: &rest)
		(= spotEgoScr captureEgo)
		((ScriptID 80 5)
			noun: 1
			okToCheck: CheckCode
			actions: guardDoVerb
		)
		((ScriptID 80 6)
			noun: 1
			okToCheck: CheckCode
			actions: guardDoVerb
		)
		((ScriptID 81 0)
			guard1Code: path1Code
			guard2Code: path1Code
			setupGuards:
		)
		(clownDoor init: stopUpd:)
		(grandHallDoor init: stopUpd:)
		(ego
			init:
			actions: egoDoVerb
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(switch prevRoomNum
			(720 (ego posn: 175 116))
			(710 (ego posn: 16 184))
			(780 (ego posn: 232 122))
			(else 
				(= local1 (= local2 1))
				(ego posn: 48 58 setScale: scaleX: 107 scaleY: 107)
			)
		)
		(if (IsObject (ego scaler?)) ((ego scaler?) doit:))
		(if
			(and
				((ScriptID 81 0) tstFlag: 709 4)
				(not ((ScriptID 81 0) tstFlag: 709 8))
			)
			(self setScript: weddingCorralCrunch)
		)
		(if ((ScriptID 80 0) tstFlag: 711 -32768)
			(if script (followedClown next: script))
			(self setScript: followedClown)
		)
		(if (not script) (theGame handsOn:))
	)
	
	(method (doit &tmp temp0)
		(= local0 (ego onControl: 1))
		(cond 
			(script 0)
			((& local0 $4000) (curRoom newRoom: 720))
			((and (not local7) (& local0 $3000)) (self setScript: climbStairs))
			((& local0 $0800) (curRoom newRoom: 780))
			(
			(and (InRect 61 45 137 130 ego) (ego isStopped:)) (self setScript: climbStairs 0 1))
		)
		(if
			(and
				(InRect 61 45 136 137 ego)
				(ego isBlocked:)
				(!= script captureEgo)
			)
			(if (= temp0 (cast firstTrue: #perform RoomCheck))
				(self spotEgo: temp0)
			else
				(ego ignoreActors: 1)
			)
		)
		(if local1
			(if (ego inRect: 0 0 85 78)
				(= local3 0)
				(if (not local2)
					(= local2 1)
					(ego oldScaleSignal: 0 setScale: scaleX: 107 scaleY: 107)
				)
			else
				(= local2 0)
				(if (not local3)
					(= local3 1)
					(ego
						oldScaleSignal: 0
						setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
					)
				)
			)
		)
		(cond 
			((and local1 (& local0 $0008)) (= local1 0))
			((and (not local1) (& local0 $1000)) (= local1 1))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		((ScriptID 80 5) actions: 0)
		((ScriptID 80 6) actions: 0)
		((ScriptID 80 0) clrFlag: 711 -32768)
		(super newRoom: n &rest)
	)
	
	(method (scriptCheck param1)
		(return
			(cond 
				((OneOf param1 87 908)
					(if
						(or
							((ScriptID 81 0) tstFlag: 709 1)
							((ScriptID 81 0) tstFlag: 709 2)
							((ScriptID 81 0) tstFlag: 709 4)
						)
						(messager say: 3 14 1 0 0 899)
						(return 0)
					else
						(return (super scriptCheck: param1 &rest))
					)
				)
				(
					(or
						((ScriptID 81 0) tstFlag: 709 1)
						((ScriptID 81 0) tstFlag: 709 2)
					)
					(messager say: 0 0 6 0 0 899)
					(return 0)
				)
				(else (return (super scriptCheck: param1 &rest)))
			)
		)
	)
	
	(method (doLoiter)
		(if (not argc)
			(messager say: 10 0 26)
			((ScriptID 81 0) loiterTimer: 15)
		)
	)
	
	(method (warnUser param1 param2 &tmp temp0)
		(switch param1
			(1
				(= local9 0)
				(cond 
					((>= ((ScriptID 80 0) weddingMusicCount?) 3) (= local9 30) ((ScriptID 81 0) setFlag: 709 1 2))
					((not ((ScriptID 80 0) weddingMusicCount?)) (clownDoor _approachVerbs: 0) (= local9 28))
					(else (= local9 29))
				)
				((ScriptID 81 0) warnUser: param1 10 0 local9)
			)
			(6 (messager say: 10 0 22))
			(5
				(if
					(and
						(not (== (curRoom script?) weddingCorralCrunch))
						(= temp0 (self roomToEdge: param2))
					)
					(switch temp0
						(4 (messager say: 10 0 19))
						(1 (messager say: 10 0 18))
					)
				)
			)
			(4
				(if (not ((ScriptID 81 0) tstFlag: 709 4))
					(if ((ScriptID 81 0) tstFlag: 709 2)
						(messager say: 10 0 23)
					else
						(messager say: 10 0 21)
					)
				)
			)
			(else 
				(super warnUser: param1 &rest)
			)
		)
	)
)

(instance RoomCheck of Code
	(properties)
	
	(method (doit param1)
		(return
			(if (param1 isKindOf: GuardDog)
				(== ((param1 regPathID?) currentRoom?) curRoomNum)
			else
				0
			)
		)
	)
)

(instance talkToGuards of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 1)
				(register perform: (register checkCode?))
			)
			(ego setMotion: 0)
			(self cue:)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0)
				(= register (CueObj client?))
				(messager say: 1 2 0 1 self)
			)
			(1
				(= local6 1)
				(= local7 1)
				(ego setMotion: PolyPath (register x?) (register y?))
			)
			(2
				(if (not (ego facingMe: register))
					(Face ego register self)
				else
					(= cycles 1)
				)
			)
			(3 (= cycles 1))
			(4
				(messager say: 1 2 0 2 self oneOnly: 0)
			)
			(5 (curRoom spotEgo: register))
		)
	)
)

(instance captureEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(cast contains: (ScriptID 80 5))
						(cast contains: (ScriptID 80 6))
						(==
							(((ScriptID 80 5) regPathID?) currentRoom?)
							curRoomNum
						)
						(==
							(((ScriptID 80 6) regPathID?) currentRoom?)
							curRoomNum
						)
					)
					(curRoom moveOtherGuard: 1)
				)
				(= ticks 2)
			)
			(1
				(if (not local6)
					(messager say: 10 0 16 1 self)
				else
					(= cycles 1)
				)
			)
			(2 (Face register ego self))
			(3 (= cycles 14))
			(4
				(ego stopUpd:)
				(grandHallDoor setCycle: 0 stopUpd:)
				(= cycles 4)
			)
			(5
				(messager say: 10 0 16 2 self)
			)
			(6
				(register setScript: (ScriptID 80 4) self 1)
			)
			(7
				(register stopUpd:)
				(= cycles 4)
			)
			(8
				(messager say: 10 0 16 3 self)
			)
			(9
				(messager say: 10 0 16 4 self)
			)
			(10
				(cast eachElementDo: #hide)
				(curRoom drawPic: 98)
				(= cycles 4)
			)
			(11
				(messager say: 10 0 16 5 self)
			)
			(12 (curRoom newRoom: 820))
		)
	)
)

(instance weddingCorralCrunch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if (< (((ScriptID 80 6) regPathID?) value?) 8)
					(((ScriptID 80 6) regPathID?) value: 8 moveDone:)
				)
				((ScriptID 81 0) setFlag: 709 1 8)
				(messager say: 10 0 15 0 self)
			)
			(2
				((ScriptID 81 0) startGuard:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance climbStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(= register
						(if (<= (ego x?) 88) grandHallDoor else stairCase)
					)
				else
					(= register
						(if (& local0 $2000) grandHallDoor else stairCase)
					)
				)
				(ego
					setMotion: PolyPath (register approachX?) (register approachY?) self
				)
			)
			(1
				(theGame handsOn:)
				(= register 0)
				(ego oldScaleSignal: 0 reset:)
				(self dispose:)
			)
		)
	)
)

(instance followedClown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) clrFlag: 711 -32768)
				(jollo view: 717 loop: 8)
				(if (== prevRoomNum 710)
					(jollo posn: 142 150 loop: 3)
				else
					(jollo posn: 177 130 loop: 1)
				)
				(jollo
					ignoreActors: 1
					setCycle: Walk
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					init:
				)
				(= cycles 2)
			)
			(1
				(if (== ((inventory at: 25) owner?) 750)
					(self setScript: jolloClimbStairs self)
				else
					(self setScript: jolloGotoBed self)
				)
			)
			(2
				(if (not next) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance jolloClimbStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(jollo setMotion: MoveTo 132 134 self)
			)
			(1
				(jollo setMotion: PolyPath 90 134 self)
			)
			(2
				(jollo
					setScale:
					scaleX: 107
					scaleY: 107
					setMotion: PolyPath 48 55 self
				)
			)
			(3
				(soundFx2 number: 901 loop: 1 play:)
				(grandHallDoor priority: 10 setCycle: EndLoop self)
			)
			(4
				(soundFx2 stop:)
				(jollo
					setMotion: MoveTo (- (jollo x?) 15) (jollo y?) self
				)
			)
			(5
				(jollo hide:)
				(grandHallDoor priority: 0 setCycle: BegLoop self)
			)
			(6
				(soundFx2 number: 902 loop: 1 play:)
				(grandHallDoor stopUpd:)
				(if (== ((inventory at: 25) owner?) 750)
					(jollo setScript: followTimer)
				else
					(jollo dispose:)
				)
				(self dispose:)
			)
		)
	)
)

(instance followTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 0) setFlag: 711 -32768)
				(= seconds 10)
			)
			(1
				((ScriptID 80 0) clrFlag: 711 -32768)
				(jollo dispose:)
			)
		)
	)
)

(instance jolloGotoBed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(jollo setMotion: MoveTo 236 124 self)
			)
			(1
				(soundFx2 number: 901 loop: 1 play:)
				(clownDoor setCycle: EndLoop self)
			)
			(2
				(soundFx2 stop:)
				(jollo setMotion: MoveTo 238 121 self)
			)
			(3
				(jollo
					setMotion: MoveTo (+ (jollo x?) 10) (jollo y?) self
				)
			)
			(4
				(jollo dispose:)
				(clownDoor setCycle: BegLoop self)
			)
			(5
				(soundFx2 number: 902 loop: 1 play:)
				(clownDoor stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance clownDoor of Prop
	(properties
		x 237
		y 104
		noun 5
		sightAngle 45
		approachX 234
		approachY 122
		view 840
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreActors: 1 yStep: -1)
		(if (not ((ScriptID 80 0) tstFlag: 709 2))
			(self approachVerbs: 5)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if ((ScriptID 80 0) tstFlag: 709 4)
					(messager say: noun theVerb 9 0)
				else
					(messager say: noun theVerb 8 0)
				)
			)
			(5
				(cond 
					(
						(or
							((ScriptID 80 0) tstFlag: 709 2)
							((ScriptID 81 0) tstFlag: 709 4)
						)
						(messager say: noun theVerb 7 0)
					)
					(
						(or
							((ScriptID 81 0) tstFlag: 709 1)
							((ScriptID 81 0) tstFlag: 709 2)
						)
						(if ((ScriptID 80 0) tstFlag: 709 4)
							(messager say: noun theVerb 6 0 self)
						else
							(messager say: noun theVerb 5 0 self)
						)
					)
					((not ((ScriptID 80 0) tstFlag: 709 4)) (messager say: noun theVerb 3 0 self))
					(else (self cue: 0))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue param1)
		(switch (= param1 (++ yStep))
			(0
				(theGame handsOff:)
				(self ignoreActors: setCycle: EndLoop self)
				(soundFx number: 901 setLoop: 1 play:)
			)
			(1
				((ScriptID 80 0) setFlag: 709 4)
				(proc80_2 2)
			)
		)
	)
)

(instance grandHallDoor of Prop
	(properties
		x 26
		y 55
		z 36
		noun 7
		sightAngle 45
		approachX 48
		approachY 55
		view 840
		loop 1
		cycleSpeed 0
	)
	
	(method (init)
		(super init: &rest)
		(= signal (| signal fixPriOn))
		(self approachVerbs: 5)
	)
	
	(method (doit &tmp temp0 temp1)
		(= temp0 ((ScriptID 80 5) onControl: 1))
		(= temp1 ((ScriptID 80 6) onControl: 1))
		(super doit:)
		(return
			(if (not cycler)
				(cond 
					(
						(and
							cel
							(or
								(and
									((ScriptID 81 0) tstFlag: 709 1)
									(not (& temp0 $0400))
								)
								(and
									((ScriptID 81 0) tstFlag: 709 2)
									(IsObject ((ScriptID 80 6) regPathID?))
									(==
										(((ScriptID 80 6) regPathID?) currentRoom?)
										curRoomNum
									)
									(not (& temp1 $0400))
								)
							)
						)
						(self setCycle: BegLoop self)
					)
					(
						(and
							(not cel)
							(or
								(and
									((ScriptID 81 0) tstFlag: 709 1)
									((ScriptID 80 5) mover?)
									(& temp0 $0400)
								)
								(and
									((ScriptID 81 0) tstFlag: 709 2)
									(IsObject ((ScriptID 80 6) regPathID?))
									(==
										(((ScriptID 80 6) regPathID?) currentRoom?)
										curRoomNum
									)
									(& temp1 $0400)
								)
							)
						)
						(self cue: 1 setCycle: EndLoop)
					)
					(else 0)
				)
			else
				0
			)
		)
	)
	
	(method (doVerb theVerb)
		(= local7 0)
		(switch theVerb
			(5
				(theGame handsOff:)
				(curRoom newRoom: 730)
			)
			(1
				(if (not local4)
					(++ local4)
					(= _approachVerbs
						(| _approachVerbs (approachCode doit: 1))
					)
					(messager say: noun theVerb 11)
				else
					(ego
						oldScaleSignal: 0
						oldMaxScale: 0
						oldBackSize: 0
						oldFrontY: 0
						oldBackY: 0
					)
					(theGame handsOff:)
					(messager say: noun theVerb 10 1)
					(curRoom setScript: (ScriptID 82) 0 lookIntoGrandHall)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event &tmp temp0)
		(= local7 0)
		(if
			(and
				(= temp0 (super onMe: event))
				(&
					_approachVerbs
					(approachCode doit: ((theIconBar curIcon?) message?))
				)
			)
			(= local7 1)
		)
		(return temp0)
	)
	
	(method (cue)
		(self ignoreActors: (not (& signal $4000)))
		(if (not local5)
			(if argc
				(= priority 10)
				(soundFx2 number: 901 loop: 1 play:)
			else
				(= priority 0)
				(self stopUpd:)
				(soundFx2 number: 902 loop: 1 play:)
			)
		else
			(curRoom newRoom: 730)
		)
	)
)

(instance keyHoleActions of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(switch theVerb
			(1
				(if ((ScriptID 80 0) tstFlag: 709 2)
					(messager say: 8 theVerb 32)
				else
					(= temp0 0)
				)
			)
			(else  (= temp0 0))
		)
		(return temp0)
	)
)

(instance lookIntoGrandHall of Script
	(properties)
	
	(method (dispose)
		(if register
			(= register 0)
			(tempGuard1 dispose:)
			(tempGuard2 dispose:)
		)
		(accessaryView dispose:)
		(if ((ScriptID 80 0) tstFlag: 711 -32768)
			(jollo dispose:)
		)
		((ScriptID 80 0) clrFlag: 711 -32768)
		(super dispose:)
		((ScriptID 80 0) stopTimers: 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 0) stopTimers: 1)
				((ScriptID 82 1)
					noun: 8
					actions: keyHoleActions
					init: 793 0 0 74 46
				)
				(jollo setScript: 0)
				(accessaryView init:)
				(if
				(not (= register ((ScriptID 80 0) tstFlag: 709 2)))
					(tempGuard1 init: stopUpd:)
					(tempGuard2 init: stopUpd:)
				)
				(= cycles 2)
			)
			(1
				(= local9 (if register 32 else 10))
				(messager say: 7 1 local9 2 self)
			)
			(2
				(if ((ScriptID 80 0) tstFlag: 711 -32768)
					(jollo
						view: 717
						loop: 0
						cel: 5
						x: 143
						y: 96
						setScale:
						scaleX: 49
						scaleY: 49
						priority: 12
						setCycle: Walk
						signal: 16400
						init:
						setMotion: MoveTo 173 79
					)
				)
			)
		)
	)
)

(instance accessaryView of View
	(properties
		view 793
		cel 1
		priority 11
		signal $4010
	)
	
	(method (init)
		(= x ((ScriptID 82 1) x?))
		(= y ((ScriptID 82 1) y?))
		(super init: &rest)
	)
)

(instance tempGuard1 of View
	(properties
		x 148
		y 128
		noun 8
		sightAngle 180
		view 793
		loop 1
		priority 14
		signal $4010
		scaleSignal $0001
	)
)

(instance tempGuard2 of View
	(properties
		x 167
		y 126
		noun 8
		sightAngle 180
		view 793
		loop 1
		cel 1
		priority 14
		signal $4010
		scaleSignal $0001
	)
)

(instance stairCase of Feature
	(properties
		x 129
		y 136
		z 24
		noun 11
		nsTop 90
		nsLeft 123
		nsBottom 135
		nsRight 136
		sightAngle 45
		approachX 141
		approachY 137
	)
)

(instance roomFeatures of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (onMe event &tmp temp0)
		(cond 
			(
				(or
					(and
						(&
							$0010
							(= temp0 (OnControl 4 (event x?) (event y?)))
						)
						(= noun 13)
					)
					(and (& $0002 temp0) (= noun 4))
				)
			)
			((& $7804 temp0) (= noun 12))
		)
	)
)

(instance floors of Feature
	(properties
		noun 6
		onMeCheck $0188
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(= local7 0)
		(if
			(and
				(& (event type?) evMOVE)
				(not (event modifiers?))
				(self onMe: event)
			)
			(= local7 1)
		else
			(super handleEvent: event)
		)
	)
)

(instance jollo of Actor
	(properties)
)

(instance guardScalerCode of Code
	(properties)
	
	(method (doit param1)
		(cond 
			((param1 inRect: 0 0 88 86)
				(if (param1 scaler?)
					(param1 setScale: scaleX: 107 scaleY: 107)
				)
			)
			((not (param1 scaler?))
				(param1
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
				)
				((param1 scaler?) doit:)
			)
		)
	)
)

(instance CheckCode of Code
	(properties)
	
	(method (doit param1)
		(return
			(if
				(and
					(param1 regPathID?)
					(== ((param1 regPathID?) currentRoom?) curRoomNum)
					(not (param1 inRect: 136 64 174 118))
					(not (param1 inRect: -20 0 35 67))
				)
				(> (param1 x?) 0)
			else
				0
			)
		)
	)
)

(instance path1Code of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(= temp1 0)
		(= temp0 (param1 onControl: 1))
		(if (param1 regPathID?)
			(if
			(and (not local1) (!= (curRoom script?) climbStairs))
				(= temp1
					(if
						(and
							(== ((param1 regPathID?) currentRoom?) curRoomNum)
							(& temp0 $0188)
						)
						(cond 
							((== (param1 loop?) 3) (<= (ego y?) (param1 y?)))
							((== local0 temp0))
							(else (& (| local0 temp0) $0100))
						)
					else
						0
					)
				)
			else
				(= temp1
					(if
					(== ((param1 regPathID?) currentRoom?) curRoomNum)
						(param1 inRect: 0 0 119 131)
					else
						0
					)
				)
			)
		)
		(return temp1)
	)
)

(instance egoDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(if (OneOf theVerb 14 19)
			(if
				(or
					((ScriptID 81 0) tstFlag: 709 1)
					((ScriptID 81 0) tstFlag: 709 2)
					((ScriptID 81 0) tstFlag: 709 4)
				)
				(messager say: 3 14 1)
			else
				(= temp0 0)
			)
		else
			(= temp0 0)
		)
		(return temp0)
	)
)

(instance guardDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(if (== theVerb 2)
			(curRoom setScript: talkToGuards)
		else
			(= temp0 0)
		)
		(return temp0)
	)
)
