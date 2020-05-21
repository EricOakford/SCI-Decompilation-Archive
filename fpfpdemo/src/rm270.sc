;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include game.sh) (include "270.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm270 0
)

(local
	local0
	roomPts
)
(instance rm270 of FPRoom
	(properties
		noun N_ROOM
		picture 270
		style FADEOUT
		horizon 40
		west 250
	)
	
	(method (init)
		(self setRegions: rgFreddy)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(super init:)
		(= roomPts
			((Polygon new:)
				type: PBarredAccess
				init:
					0 166
					193 163
					68 94
					62 54
					41 44
					29 49
					53 59
					54 73
					0 70
					0 0
					319 0
					319 189
					0 189
				yourself:
			)
		)
		(curRoom addObstacle: roomPts)
		(tree
			init:
			setOnMeCheck: dynamicName cRED
		)
		(tracks
			init:
			setOnMeCheck: dynamicName cCYAN
		)
		(shrubs
			init:
			setOnMeCheck: dynamicName cGREY
		)
		(swamp
			init:
			setOnMeCheck: dynamicName cLGREEN
		)
		(train
			init:
			setOnMeCheck: dynamicName cLBLUE
		)
		(waterTower
			init:
			setOnMeCheck: dynamicName cLGREY
		)
		(rope init: setPri: 4)
		(if
			(and
				(not (ego has: iFuse))
				(not (ego has: iBomb))
				(not (ego has: iUnlitBomb))
			)
			(fuse init: approachVerbs: V_DO)
		)
		(bubble init: hide: setScript: sGurgle)
		(bucket init: setPri: 4)
		(mine init:)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 10 1)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BOMB_LIT
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance sGurgle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client show: setCycle: RandCycle)
				(theMusic number: 270 loop: 1 play: self)
			)
			(1
				(client hide:)
				(= seconds (Random 5 10))
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance sEgoSwings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (+ (bucket x?) 10) (+ (bucket y?) 47) self
				)
			)
			(1
				(if register (ego posn: 247 10) else (ego posn: 33 8))
				(bucket hide:)
				(ego
					view: 271
					setScale: 0
					setLoop: 2
					cel: 0
					setPri: 5
					setCycle: EndLoop self
				)
			)
			(2
				(squeakSound play:)
				(ego setLoop: 3)
				(if register
					(ego setCycle: Reverse setMotion: MoveTo 33 8 self)
				else
					(ego setCycle: Forward setMotion: MoveTo 247 10 self)
				)
			)
			(3
				(if register
					((curRoom obstacles?) delete: local0)
					(local0 dispose:)
					(curRoom
						addObstacle:
							(= roomPts
								((Polygon new:)
									type: PBarredAccess
									init:
										0 166
										193 163
										68 94
										62 54
										41 44
										29 49
										53 59
										54 73
										0 70
										0 0
										319 0
										319 189
										0 189
									yourself:
								)
							)
					)
				else
					((curRoom obstacles?) delete: roomPts)
					(roomPts dispose:)
					(curRoom
						addObstacle:
							(= local0
								((Polygon new:)
									type: PBarredAccess
									init:
										0 166
										193 163
										68 94
										62 54
										41 44
										29 49
										53 59
										54 73
										0 70
										0 0
										268 0
										268 39
										248 57
										262 60
										274 40
										273 0
										319 0
										319 189
										0 189
									yourself:
								)
							)
					)
				)
				(squeakSound stop:)
				(if register
					(bucket posn: 33 8 show:)
				else
					(bucket posn: 247 10 show:)
				)
				(ego setLoop: 2 cel: (ego lastCel:) setCycle: BegLoop self)
			)
			(4
				(ego
					posn: (+ (bucket x?) 10) (+ (bucket y?) 47)
					normalize:
					setScale: Scaler 100 50 175 95
				)
				(= cycles 5)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExplode of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(bubble setScript: 0)
				(theMusic stop:)
				(ego setMotion: PolyPath 261 37 self)
			)
			(1
				(Face ego mine)
				(= cycles 5)
			)
			(2
				(ego view: 271 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego put: iBomb)
				(explodeSound play:)
				(ShakeScreen 5 shakeSDiagonal)
				(ShakeScreen 6 shakeSDown)
				(ShakeScreen 7 shakeSRight)
				(ShakeScreen 4 shakeSDiagonal)
				(= cycles 5)
			)
			(4
				(soundFx pause: TRUE)
				(mine setCycle: EndLoop self)
			)
			(5
				(mine setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(6
				(mine setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(mine setLoop: 3 cel: 0 addToPic:)
				(= ticks 120)
			)
			(8
				(sounds eachElementDo: #fade 30 10 12 1)
				(cast eachElementDo: #dispose)
				(timers eachElementDo: #dispose)
				(theMusic
					number: (if (> numVoices 11) 910 else 1910)
					loop: -1
					play:
				)
				(curRoom drawPic: 780)
				(theGame setCursor: INVIS_CURSOR TRUE)
				(= cycles 5)
			)
			(9
				((ScriptID tlkWilly 1) disposeWhenDone: FALSE)
				(messager say: N_MINE V_BOMB_LIT NULL 0 self)
			)
			(10
				((ScriptID tlkWilly 1) disposeWhenDone: TRUE)
				(curRoom newRoom: 105)
				(self dispose:)
			)
		)
	)
)

(instance egoTossCan of Actor
	(properties
		x 246
		y 33
		view 271
		loop 4
		cel 7
	)
)

(instance bubble of Prop
	(properties
		x 186
		y 118
		noun N_SWAMP
		view 270
		cel 4
		cycleSpeed 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BOMB_LIT
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance mine of Prop
	(properties
		x 266
		y 6
		z -10
		noun N_MINE
		view 272
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BOMB_LIT
				(if (> (ego x?) 200)
					(curRoom setScript: sExplode)
				else
					(messager say: noun theVerb C_WRONGSIDE)
				)
			)
			(23
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance rope of View
	(properties
		x 28
		y 9
		noun N_ROPE
		view 271
		loop 1
		signal ignrAct
	)
)

(instance bucket of View
	(properties
		x 33
		y 8
		noun N_BUCKET
		view 271
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (> (ego x?) 200)
					(curRoom setScript: sEgoSwings 0 1)
				else
					(curRoom setScript: sEgoSwings 0 0)
				)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance fuse of View
	(properties
		x 262
		y 39
		noun N_FUSE
		view 271
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (> (ego x?) 200)
					(ego get: 13 self)
					(self dispose:)
				else
					(messager say: noun theVerb C_WRONGSIDE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tracks of Feature
	(properties
		x 1
		y 1
		noun N_TRACKS
	)
)

(instance tree of Feature
	(properties
		x 1
		y 1
		noun N_TREE
	)
)

(instance waterTower of Feature
	(properties
		x 1
		y 1
		noun N_WATERTOWER
	)
)

(instance shrubs of Feature
	(properties
		x 1
		y 1
		noun N_SHRUBS
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BOMB_LIT
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance train of Feature
	(properties
		x 1
		y 1
		noun N_TRAIN
	)
)

(instance swamp of Feature
	(properties
		x 1
		y 1
		noun N_SWAMP
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BOMB_LIT
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance explodeSound of Sound
	(properties
		flags mNOPAUSE
		number 546
	)
)

(instance squeakSound of Sound
	(properties
		flags mNOPAUSE
		number 909
		loop -1
	)
)
