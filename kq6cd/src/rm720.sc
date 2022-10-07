;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
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
	rm720 0
)

(local
	local0
	local1
)
(instance rm720 of CastleRoom
	(properties
		noun 7
		picture 720
		style $000a
		east 840
		west 710
		vanishingY -400
		minScaleSize 75
		minScaleY 143
		maxScaleY 189
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						-10
						319
						-10
						319
						139
						296
						139
						290
						142
						214
						142
						209
						139
						89
						139
						86
						142
						39
						142
						27
						147
						0
						147
					yourself:
				)
		)
		(features
			add: statue ceiling theWindows floorOrCeiling
			eachElementDo: #init
		)
		((ScriptID 80 5) noun: 5)
		((ScriptID 80 6) noun: 5)
		((ScriptID 81 0)
			guard1Code: path1Code
			guard2Code: path1Code
			setupGuards:
		)
		(if ((ScriptID 81 0) tstFlag: 709 4)
			((ScriptID 80 0) weddingRemind: 0)
			((ScriptID 80 5)
				setMotion: 0
				posn: 131 154 0
				loop: 3
				okToCheck: 1
				init:
				setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
			)
			((ScriptID 80 6)
				setMotion: 0
				posn: 140 146 0
				loop: 2
				okToCheck: 1
				init:
				setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
			)
			(= moveOtherGuard 1)
		)
		(guardDoor init: stopUpd:)
		(super init: &rest)
		(hiddenDoor init:)
		(arm init:)
		(= spotEgoScr captureEgo)
		(= local1 1)
		(ego
			init:
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(switch prevRoomNum
			(800
				(= local1 0)
				(arm startUpd: cel: 1 stopUpd:)
				(hiddenDoor startUpd: cel: 7 stopUpd:)
				(ego posn: 48 144)
				(self setScript: closeDoor)
				(if ((ScriptID 80 0) tstFlag: 710 256)
					((ScriptID 80 0) setFlag: 711 512)
					((ScriptID 80 0) weddingRemind: 121)
				)
			)
			(840 (ego posn: 305 168))
			(else  (ego posn: 13 167))
		)
		(if
			(and
				(cast contains: (ScriptID 80 6))
				(IsObject ((ScriptID 80 6) regPathID?))
				((ScriptID 80 6) mover?)
				(< (((ScriptID 80 6) regPathID?) value?) 6)
			)
			(guardDoor cel: 4)
			((ScriptID 80 6) posn: 108 142)
			(((ScriptID 80 6) regPathID?) value: 6 moveDone:)
		)
		((ego scaler?) doit:)
		(if
			(and
				local1
				(not ((ScriptID 81 0) tstFlag: 709 4))
				(not ((ScriptID 81 0) tstFlag: 709 1))
				(not ((ScriptID 81 0) tstFlag: 709 2))
				(not (Random 0 5))
			)
			((ScriptID 81 0) setFlag: 709 1 loiterTimer: 1)
		)
		(if (not script) (theGame handsOn:))
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 964)
	)
	
	(method (newRoom n)
		(if
			(and
				(== n 840)
				(>= ((ScriptID 80 0) weddingMusicCount?) 3)
				(not ((ScriptID 81 0) tstFlag: 709 2))
			)
			((ScriptID 81 0) setFlag: 709 2)
			((ScriptID 80 0)
				weddingRemind: (+ ((ScriptID 80 0) weddingRemind?) 9)
			)
		)
		(super newRoom: n)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(return
			(cond 
				((OneOf param1 87 908)
					(if
						(or
							((ScriptID 81 0) tstFlag: 709 1)
							((ScriptID 81 0) tstFlag: 709 2)
							((ScriptID 81 0) tstFlag: 709 4)
						)
						(messager say: 1 14 1 0 0 899)
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
	
	(method (warnUser param1 param2 &tmp temp0 temp1)
		(switch param1
			(5
				(if (= temp0 (self roomToEdge: [param2 0]))
					(switch temp0
						(2 (messager say: 10 0 17))
					)
				)
			)
			(6
				(if (not ((ScriptID 81 0) tstFlag: 709 4))
					(if (== param2 7)
						(messager say: 10 0 22)
					else
						(messager say: 10 0 24)
					)
				)
			)
			(1
				(cond 
					((>= ((ScriptID 80 0) weddingMusicCount?) 3)
						(if (>= ((ScriptID 80 0) weddingMusicCount?) 4)
							(theGame handsOff:)
							((ScriptID 81 0) setFlag: 709 2)
						else
							(= temp1 33)
							((ScriptID 80 0) weddingRemind: 15)
						)
					)
					((not ((ScriptID 80 0) weddingMusicCount?)) (= temp1 28))
					(else (= temp1 29))
				)
				((ScriptID 81 0) warnUser: param1 10 0 temp1)
			)
			(4
				(if (not ((ScriptID 81 0) tstFlag: 709 4))
					((ScriptID 81 0) clrFlag: 709 2)
					((ScriptID 80 6) dispose:)
					(messager say: 10 0 23)
				)
			)
			(else 
				(super warnUser: param1 param2 &rest)
			)
		)
	)
)

(instance enterGuardDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 81 0) resetGuard: (ScriptID 80 5) 1)
				((ScriptID 81 0) resetGuard: (ScriptID 80 6) 2)
				(messager say: 4 5 4 1 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(soundFx2 number: 901 loop: 1 play:)
				(guardDoor setCycle: EndLoop self)
			)
			(3
				(soundFx2 stop:)
				(messager say: 4 5 4 2 self)
			)
			(4
				(ego setSpeed: 5 setMotion: MoveTo 37 158)
				(= ticks 90)
			)
			(5
				((ScriptID 80 5)
					init:
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 130 135 0
					illegalBits: 0
					setSpeed: 5
					setMotion: MoveTo 109 135 self
				)
			)
			(6
				((ScriptID 80 5) setMotion: MoveTo 107 140 self)
			)
			(7 (messager say: 4 5 4 3 self))
			(8
				(theGame handsOff:)
				(Face ego (ScriptID 80 5) self)
			)
			(9 (= cycles 4))
			(10
				((ScriptID 80 5) setScript: (ScriptID 80 4))
			)
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
						(IsObject ((ScriptID 80 5) regPathID?))
						(==
							(((ScriptID 80 5) regPathID?) currentRoom?)
							curRoomNum
						)
						(IsObject ((ScriptID 80 6) regPathID?))
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
				(messager say: 10 0 16 1 self)
			)
			(2 (Face register ego self))
			(3
				(messager say: 10 0 16 2 self)
			)
			(4
				(register setScript: (ScriptID 80 4) self 1)
			)
			(5
				(messager say: 10 0 16 3 self)
			)
			(6
				(messager say: 10 0 16 4 self)
			)
			(7
				(cast eachElementDo: #hide)
				(curRoom drawPic: 98)
				(= cycles 4)
			)
			(8
				(messager say: 10 0 16 5 self)
			)
			(9 (curRoom newRoom: 820))
		)
	)
)

(instance statue of Feature
	(properties
		x 23
		y 144
		z 30
		noun 11
		sightAngle 45
		onMeCheck $0002
		approachX 9
		approachY 147
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
)

(instance arm of View
	(properties
		x 18
		y 101
		noun 8
		approachX 8
		approachY 147
		view 720
		loop 2
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(if (== (approachCode doit: theVerb) -32768)
			(messager say: 11)
		else
			(switch theVerb
				(2 (messager say: 11 theVerb))
				(5
					(cond 
						((not ((ScriptID 80 0) tstFlag: 709 -32768)) (messager say: noun theVerb 9))
						(((ScriptID 80 0) tstFlag: 709 2) (messager say: noun theVerb 7))
						(else (curRoom setScript: openSecretDoor))
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance closeDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(soundFx2 number: 909 loop: 1 play:)
				(hiddenDoor setCycle: BegLoop self)
			)
			(2
				(soundFx2 stop:)
				(arm cel: 0 forceUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openSecretDoor of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 5) okToCheck: 0 setMotion: 0)
				((ScriptID 80 6) okToCheck: 0 setMotion: 0)
				(if (not ((ScriptID 80 0) tstFlag: 711 2))
					(theGame givePoints: 2)
					(messager say: 8 5 5 1 self)
				else
					(messager say: 8 5 6 0 self)
				)
			)
			(1
				(ego
					normal: 0
					view: 722
					loop: 0
					cel: 0
					posn: 5 146
					cycleSpeed: 8
					setScale:
					scaleX: 107
					scaleY: 107
					setCycle: CycleTo 3 1 self
				)
				(arm startUpd:)
			)
			(2
				(soundFx2 number: 720 loop: 1 play:)
				(arm cel: 1 stopUpd:)
				(ego setCycle: BegLoop self)
				(hiddenDoor startUpd:)
			)
			(3
				(soundFx2 number: 909 loop: 1 play:)
				(hiddenDoor setCycle: EndLoop self)
			)
			(4
				(soundFx2 stop:)
				(hiddenDoor stopUpd:)
				(ego reset: 3 900)
				(= cycles 1)
			)
			(5
				(if
					(or
						(and
							(cast contains: (ScriptID 80 5))
							(==
								(((ScriptID 80 5) regPathID?) currentRoom?)
								curRoomNum
							)
							(= temp0 (ScriptID 80 5))
						)
						(and
							(cast contains: (ScriptID 80 6))
							(==
								(((ScriptID 80 6) regPathID?) currentRoom?)
								curRoomNum
							)
							(= temp0 (ScriptID 80 6))
						)
					)
					(curRoom spotEgo: temp0)
				else
					(= ticks 1)
				)
			)
			(6
				(if (not ((ScriptID 80 0) tstFlag: 711 2))
					((ScriptID 80 0) setFlag: 711 2)
					(messager say: 8 5 5 2 self)
				else
					(= cycles 1)
				)
			)
			(7
				(ego
					setSpeed: 8
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					setMotion: PolyPath 51 141 self
				)
			)
			(8 (ego setHeading: 0 self))
			(9
				(ego
					normal: 0
					view: 722
					loop: 1
					cel: 0
					cycleSpeed: 8
					posn: 51 143
					setScale:
					scaleX: 106
					scaleY: 106
					setCycle: EndLoop self
				)
			)
			(10
				(ego dispose:)
				(= cycles 4)
			)
			(11 (curRoom newRoom: 800))
		)
	)
)

(instance hiddenDoor of Prop
	(properties
		x 40
		y 117
		noun 9
		view 720
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== (approachCode doit: theVerb) -32768)
			(= theVerb 0)
		)
		(messager say: noun theVerb 10)
	)
	
	(method (onMe event)
		(if (super onMe: event)
			((ScriptID 80 0) tstFlag: 711 2)
		)
	)
)

(instance guardDoor of Prop
	(properties
		x 99
		y 117
		noun 4
		sightAngle 40
		approachX 110
		approachY 139
		view 720
		cycleSpeed 0
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 1)
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
							(not cel)
							(or
								(and
									((ScriptID 81 0) tstFlag: 709 1)
									(IsObject ((ScriptID 80 5) regPathID?))
									(==
										(((ScriptID 80 5) regPathID?) currentRoom?)
										curRoomNum
									)
									(& temp0 $4000)
								)
								(and
									((ScriptID 81 0) tstFlag: 709 2)
									(IsObject ((ScriptID 80 6) regPathID?))
									(==
										(((ScriptID 80 6) regPathID?) currentRoom?)
										curRoomNum
									)
									(& temp1 $4000)
								)
							)
						)
						(self cue: 1 setCycle: EndLoop)
					)
					(
						(and
							cel
							(or
								(and
									((ScriptID 81 0) tstFlag: 709 1)
									(IsObject ((ScriptID 80 5) regPathID?))
									(==
										(((ScriptID 80 5) regPathID?) currentRoom?)
										curRoomNum
									)
									(not (& temp0 $4000))
								)
								(and
									((ScriptID 81 0) tstFlag: 709 2)
									(IsObject ((ScriptID 80 6) regPathID?))
									(==
										(((ScriptID 80 6) regPathID?) currentRoom?)
										curRoomNum
									)
									(not (& temp1 $4000))
								)
							)
						)
						(self setCycle: BegLoop self)
					)
					(else 0)
				)
			else
				0
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if local0
					(curRoom setScript: enterGuardDoor)
				else
					(messager say: noun theVerb (+ 3 local0))
				)
				(= local0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if argc
			(soundFx2 number: 901 loop: 1 play:)
		else
			(soundFx2 number: 902 loop: 1 play:)
		)
	)
)

(instance ceiling of Feature
	(properties
		y 2
		noun 2
		onMeCheck $0010
	)
)

(instance rightArm of Feature
	(properties
		y 144
		noun 8
		onMeCheck $0008
		approachX 9
		approachY 147
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (onMe event)
		(= x (event x?))
		(super onMe: event)
	)
)

(instance theWindows of Feature
	(properties
		y 75
		noun 13
		onMeCheck $0004
	)
	
	(method (onMe event)
		(= x (event x?))
		(super onMe: event)
	)
)

(instance floorOrCeiling of Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (onMe event)
		(cond 
			((and (<= (event y?) 137) (= noun 12)))
			((> (event y?) 137) (= noun 3))
		)
	)
)

(instance path1Code of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(= temp0 0)
		(if
			(or
				(not (IsObject (param1 regPathID?)))
				(== ((param1 regPathID?) currentRoom?) curRoomNum)
			)
			(= temp0
				(if (>= 320 (param1 x?)) (>= (param1 x?) 0) else 0)
			)
		)
		(return temp0)
	)
)
