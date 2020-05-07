;;; Sierra Script 1.0 - (do not remove this comment)
(script# 710)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use PanelInset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm710 0
)

(local
	[local0 9] = [1 12 9 28 5 2 21]
	local9
	local10
	local11
	local12
)
(class DungeonDoor of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 4
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 45
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 710
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 8
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		dungeon_ 0
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 35 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(cond 
					(
						(or
							((ScriptID 80 0) tstFlag: 709 2)
							((ScriptID 81 0) tstFlag: 709 4)
						)
						(messager say: noun theVerb 8 1)
					)
					((not ((ScriptID 80 0) tstFlag: 709 2))
						((ScriptID 80 0) dungeonEntered: dungeon_)
						(curRoom setScript: enterDungeon 0 self)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rm710 of CastleRoom
	(properties
		noun 8
		picture 710
		style $000a
		north 720
		east 840
		minScaleSize 37
		minScaleY 112
		maxScaleY 173
	)
	
	(method (init &tmp temp0 temp1)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						176
						243
						176
						192
						141
						198
						141
						177
						128
						171
						128
						152
						115
						177
						115
						177
						111
						102
						111
						75
						129
						70
						129
						48
						143
						54
						143
						0
						179
					yourself:
				)
		)
		(features add: armour roomStuff eachElementDo: #init)
		((ScriptID 80 6) noun: 13 actions: guardDoVerb)
		((ScriptID 81 0) guard2Code: path2Code setupGuards:)
		(= spotEgoScr captureEgo)
		(super init: &rest)
		(dungeonDoor1 init: stopUpd:)
		(dungeonDoor2 init: stopUpd:)
		(dungeonDoor3 init: stopUpd:)
		(treasureDoor init: stopUpd:)
		(= temp1 0)
		(ego
			init:
			reset:
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(switch prevRoomNum
			(770
				(ego posn: 65 134)
				(if ((ScriptID 81 0) tstFlag: 709 4)
					((ScriptID 81 0) setFlag: 709 2)
					((ScriptID 81 0) startGuard:)
				)
			)
			(720
				(if (not ((ScriptID 80 0) tstFlag: 711 512))
					(= temp1 1)
				)
				(ego posn: 148 114)
			)
			(820
				((ScriptID 80 0) clrFlag: 709 8192)
				(switch ((ScriptID 80 0) dungeonEntered?)
					(1
						(ego
							posn: (dungeonDoor1 approachX?) (dungeonDoor1 approachY?)
						)
					)
					(2
						(ego
							posn: (dungeonDoor2 approachX?) (dungeonDoor2 approachY?)
						)
					)
					(3
						(ego
							posn: (dungeonDoor3 approachX?) (dungeonDoor3 approachY?)
						)
						(cond 
							(((ScriptID 80 0) tstFlag: 709 1)
								((ScriptID 80 0) clrFlag: 709 1)
								(jollo
									view: 717
									loop: 4
									cel: 2
									posn: 170 154
									setScale:
										Scaler
										(curRoom maxScaleSize?)
										(curRoom minScaleSize?)
										(curRoom maxScaleY?)
										(curRoom minScaleY?)
									init:
									ignoreActors: 1
									setCycle: StopWalk -1
								)
								(self setScript: jolloHelpedEgo)
							)
							(((ScriptID 80 0) tstFlag: 709 2)
								(theMusic fadeTo: 701 -1)
								((ScriptID 81 0) setFlag: 709 2)
								(self setScript: tempScript)
							)
						)
					)
				)
			)
			(840
				(= temp1 1)
				(ego posn: 305 184)
			)
			(else 
				(ego posn: 88 132)
				(Bset 15)
				(self setScript: (ScriptID 711 0))
			)
		)
		(if
			(and
				temp1
				(not ((ScriptID 81 0) tstFlag: 709 1))
				(not ((ScriptID 81 0) tstFlag: 709 2))
				(not (Random 0 5))
			)
			((ScriptID 81 0) setFlag: 709 2 loiterTimer: 1)
		)
		((ego scaler?) doit:)
		(if (not script) (theGame handsOn:))
	)
	
	(method (doit)
		(= local9 (ego onControl: 1))
		(cond 
			(script 0)
			((& local9 $4000) (curRoom newRoom: 720))
			((& local9 $2000) (curRoom newRoom: 820))
			((& local9 $0400) (curRoom newRoom: 770))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		((ScriptID 81 0) clrFlag: 709 16)
		(super dispose:)
		(DisposeScript 964)
	)
	
	(method (newRoom n)
		(ego setMotion: 0)
		((ScriptID 80 6) actions: 0)
		(super newRoom: n &rest)
	)
	
	(method (notify param1)
		((ScriptID 80 0) stopTimers: 0)
		(if param1
			((curRoom script?) changeState: 2)
		else
			((curRoom script?) changeState: 7)
		)
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
				(if (= temp0 (self roomToEdge: param2))
					(switch temp0
						(1 (messager say: 9 0 19))
						(2 (messager say: 9 0 18))
					)
				)
			)
			(6
				(if (== param2 8)
					(messager say: 9 0 35)
				else
					(messager say: 9 0 34)
				)
			)
			(4
				(if ((ScriptID 81 0) tstFlag: 709 1)
					((ScriptID 81 0) clrFlag: 709 1)
					((ScriptID 80 5) dispose:)
					(messager say: 9 0 37)
				else
					((ScriptID 81 0) clrFlag: 709 2)
					((ScriptID 80 6) dispose:)
					(messager say: 9 0 36)
				)
			)
			(1
				(= temp1 0)
				(cond 
					((>= ((ScriptID 80 0) weddingMusicCount?) 3)
						(if (not (cast contains: alphaInset))
							((ScriptID 81 0) setFlag: 709 2)
						else
							((ScriptID 81 0) setFlag: 709 16)
						)
					)
					((not ((ScriptID 80 0) weddingMusicCount?))
						(if (== prevRoomNum 770)
							((ScriptID 80 0) weddingMusicCount: 2 weddingRemind: 2)
						)
						(= temp1 24)
					)
					(else (= temp1 25))
				)
				((ScriptID 81 0) warnUser: param1 9 0 temp1)
			)
			(else 
				(super warnUser: param1 &rest)
			)
		)
	)
)

(instance tempScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				((ScriptID 81 0) startGuard:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterDungeon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 5 7 0 self)
			)
			(1
				(soundFx2 number: 821 loop: 1 play:)
				(ego setPri: (ego priority?))
				(register setCycle: End)
				(ego
					view: 711
					normal: 0
					loop: 1
					cel: 0
					cycleSpeed: 8
					posn: (+ (ego x?) 2) (+ (ego y?) 7)
					setCycle: End self
				)
			)
			(2 (ego dispose:) (= cycles 4))
			(3 (curRoom newRoom: 820))
		)
	)
)

(instance doTreasureDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 11 2 31 0 self)
			)
			(1
				(= local10 ((ScriptID 81 0) loiterTimer?))
				((ScriptID 81 0) loiterTimer: 0)
				((ScriptID 80 0) stopTimers: 1)
				(theGame handsOn:)
				(theIconBar disable: 0 2 3 4 5)
				(alphaInset
					view: 713
					offsetX: 30
					offsetY: 20
					maxCol: 5
					maxRow: 5
					numButtons: 30
					posn: 87 39
					init: @local0 7
				)
			)
			(2
				(theGame handsOff:)
				(= cycles 2)
			)
			(3 (messager say: 2 5 4 1 self))
			(4
				(soundFx2 number: 901 loop: 1 play:)
				(treasureDoor cycleSpeed: 10 setCycle: End self)
			)
			(5
				(soundFx2 stop:)
				(messager say: 2 5 4 2 self)
			)
			(6
				(proc80_2 4)
				(self dispose:)
			)
			(7
				(theGame handsOff:)
				(= cycles 2)
			)
			(8 (messager say: 2 5 6 0 self))
			(9
				(theGame handsOn:)
				(if ((ScriptID 81 0) tstFlag: 709 16)
					((ScriptID 81 0) setFlag: 709 2 clrFlag: 709 16)
					((ScriptID 81 0) startGuard:)
				)
				((ScriptID 81 0) loiterTimer: local10)
				(= local10 0)
				(self dispose:)
			)
		)
	)
)

(instance jolloHelpedEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1 (messager say: 9 0 9 0 self))
			(2
				(jollo setSpeed: 6 setMotion: MoveTo 184 180 self)
			)
			(3
				(jollo setMotion: MoveTo 343 180 self)
			)
			(4
				(if ((ScriptID 81 0) tstFlag: 709 4)
					((ScriptID 81 0) setFlag: 709 2)
					((ScriptID 81 0) startGuard:)
				)
				(if ((ScriptID 80 0) tstFlag: 709 2)
					(theMusic fadeTo: 701 -1)
					((ScriptID 81 0) setFlag: 709 2)
					((ScriptID 81 0) startGuard:)
					(jollo dispose:)
				else
					(theMusic fadeTo: 704 -1)
					(jollo hide: setScript: followTimer)
				)
				(theGame handsOn:)
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
				(= seconds 6)
			)
			(1
				((ScriptID 80 0) clrFlag: 711 -32768)
				(jollo dispose:)
			)
		)
	)
)

(instance talkToGuards of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 1) (register perform: path2Code))
			(ego setMotion: 0)
			(self cue:)
		)
	)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0 checkCode: 0)
				(= register (CueObj client?))
				(messager say: 13 2 0 1 self)
			)
			(1
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
				(messager say: 13 2 0 2 self oneOnly: 0)
			)
			(5
				(client setScript: captureEgo 0 register)
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
				(if (not register)
					(messager say: 9 0 17 1 self)
				else
					(= cycles 1)
				)
			)
			(1
				(Face (ScriptID 80 6) ego self)
			)
			(2
				(messager say: 9 0 17 2 self)
			)
			(3
				((ScriptID 80 6) setScript: (ScriptID 80 4) self 1)
			)
			(4
				(messager say: 9 0 17 3 self)
			)
			(5
				(messager say: 9 0 17 4 self)
			)
			(6
				(cast eachElementDo: #hide)
				(curRoom drawPic: 98)
				(= cycles 4)
			)
			(7
				(messager say: 9 0 17 5 self)
			)
			(8 (curRoom newRoom: 820))
		)
	)
)

(instance dungeonDoor1 of DungeonDoor
	(properties
		x 165
		y 110
		approachX 150
		approachY 121
		loop 3
		priority 8
		signal $5010
		dungeon_ 1
	)
)

(instance dungeonDoor2 of DungeonDoor
	(properties
		x 193
		y 117
		approachX 175
		approachY 133
		loop 2
		signal $5000
		dungeon_ 2
	)
)

(instance dungeonDoor3 of DungeonDoor
	(properties
		x 227
		y 134
		approachX 199
		approachY 160
		signal $5000
		dungeon_ 3
	)
)

(instance treasureDoor of Prop
	(properties
		x 52
		y 109
		noun 11
		sightAngle 45
		approachX 66
		approachY 134
		view 710
		loop 1
		signal $5000
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if
					(or
						((ScriptID 81 0) tstFlag: 709 2)
						((ScriptID 81 0) tstFlag: 709 4)
					)
					(messager say: noun 2 30 1)
				else
					(curRoom setScript: doTreasureDoor)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alphaInset of PanelInset
	(properties
		noun 2
	)
	
	(method (init)
		(theIconBar disable: 6)
		(super init: &rest)
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(if (< charCount strLen)
			(curRoom setScript: clearedInset 0 charCount)
		)
		(super dispose:)
		(= local11 (= local12 0))
	)
	
	(method (drawButton &tmp temp0)
		(= temp0 0)
		(if (not (OneOf value 26 27 29 30))
			(if
				(not
					(& [local11 (/ value 16)] (>> $8000 (mod value 16)))
				)
				(= [local11 (/ value 16)]
					(| [local11 (/ value 16)] (>> $8000 (mod value 16)))
				)
				(= temp0 (super drawButton: &rest))
				(soundFx number: 910 setLoop: 1 play:)
			else
				(= temp0 0)
			)
		)
		(return temp0)
	)
)

(instance clearedInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if register
					(messager say: 2 5 6 0 self)
				else
					(messager say: 2 5 5 0 self)
				)
			)
			(2
				(theGame handsOn:)
				((ScriptID 80 0) stopTimers: 0)
				((ScriptID 81 0) loiterTimer: local10)
				(self dispose:)
			)
		)
	)
)

(instance armour of Feature
	(properties
		x 151
		y 111
		z 10
		noun 10
		nsTop 91
		nsLeft 148
		nsBottom 111
		nsRight 155
		sightAngle 45
		approachX 149
		approachY 112
	)
)

(instance roomStuff of Feature
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
							$1000
							(= temp0 (OnControl 4 (event x?) (event y?)))
						)
						(= noun 14)
					)
					(and (& $0002 temp0) (= noun 3))
					(and (& $6404 temp0) (= noun 12))
				)
			)
			((& $0188 temp0) (= noun 5))
		)
	)
)

(instance jollo of Actor
	(properties)
)

(instance path2Code of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(= temp0 0)
		(if
			(and
				(== ((param1 regPathID?) currentRoom?) curRoomNum)
				(not (param1 inRect: 140 0 320 116))
				(>= 320 (param1 x?))
				(>= (param1 x?) 0)
			)
			(= temp0
				(if (== local9 (= temp1 (param1 onControl: 1)))
				else
					(& (| local9 temp1) $0100)
				)
			)
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
