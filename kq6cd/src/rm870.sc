;;; Sierra Script 1.0 - (do not remove this comment)
(script# 870)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use System)

(public
	rm870 0
)

(local
	local0
	local1
)
(instance rm870 of CastleRoom
	(properties
		noun 3
		picture 870
		style $000a
		east 860
		west 850
		vanishingY -400
		minScaleSize 70
		minScaleY 138
	)
	
	(method (init)
		(Load rsVIEW 723)
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
						140
						298
						140
						288
						143
						219
						143
						202
						140
						90
						140
						80
						144
						0
						144
					yourself:
				)
		)
		(features
			add: roomStuff cassima_door horse bench
			eachElementDo: #init
		)
		(= spotEgoScr walkGuardOn)
		(super init: &rest)
		(ego
			init:
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(switch prevRoomNum
			(860 (ego posn: 305 168))
			(else 
				(ego posn: 13 167)
				(cond 
					((not ((ScriptID 80 0) tstFlag: 710 64)) (self setScript: firstTime))
					((not ((ScriptID 80 0) tstFlag: 710 32)) (self setScript: stillCrying))
					(
						(and
							(not ((ScriptID 80 0) tstFlag: 711 256))
							(Random 0 1)
						)
						(curRoom spotEgo: (ScriptID 80 5))
					)
					(else ((ScriptID 80 0) guard2Timer: 15))
				)
			)
		)
		((ego scaler?) doit:)
		(if (not script) (theGame handsOn:))
		(if
			(and
				(!= (theGlobalSound number?) 710)
				((ScriptID 80 0) tstFlag: 710 4)
				(not ((ScriptID 80 0) tstFlag: 710 8))
			)
			(theMusic fadeTo: 703 -1)
		)
	)
	
	(method (doit &tmp temp0)
		(if (& (= temp0 (ego onControl: 1)) $6000)
			(curRoom newRoom: east)
		)
		(super doit: &rest)
	)
	
	(method (warnUser param1 param2)
		(switch param1
			(3
				(if (and (> argc 1) param2)
					(curRoom spotEgo: (ScriptID 80 5))
				else
					(self setScript: warnAboutGuard)
				)
			)
			(else 
				(super warnUser: param1 &rest)
			)
		)
	)
)

(instance firstTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 0) setFlag: 710 64)
				(= cycles 2)
			)
			(1 (messager say: 1 0 1 0 self))
			(2
				((ScriptID 80 0) guard2Timer: 31)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stillCrying of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register ((ScriptID 80 0) guard2Timer?))
				((ScriptID 80 0) guard2Timer: 0)
				(= cycles 2)
			)
			(1 (messager say: 1 0 6 0 self))
			(2
				(theGame handsOn:)
				((ScriptID 80 0) guard2Timer: (+ register 1))
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance bendOverToDoor of Script
	(properties)
	
	(method (dispose)
		(if (and (== client curRoom) (not next))
			((ScriptID 80 0) guardTimer: local0 guard2Timer: local1)
			(= local0 (= local1 0))
			(theGame handsOn:)
		)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not local1)
					(= local0 ((ScriptID 80 0) guardTimer?))
					(= local1 ((ScriptID 80 0) guard2Timer?))
				)
				((ScriptID 80 0) guardTimer: 0 guard2Timer: 0)
				(ego
					view: 723
					normal: 0
					loop: 0
					cel: 0
					posn: 32 154
					setScale: 0
					scaler: 0
					cycleSpeed: 9
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					reset: 3
					posn: (cassima_door approachX?) (cassima_door approachY?)
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
				)
				((ego scaler?) doit:)
				(= cycles 2)
			)
			(2
				(if next (next next: self))
				(self dispose:)
			)
		)
	)
)

(instance giveDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 6 8 11 0 self)
				(theGame givePoints: 3)
				(ego put: 8 870)
				(= next 0)
			)
			(1
				(theGame handsOn:)
				((ScriptID 80 0) guardTimer: local0 guard2Timer: local1)
				(= local0 (= local1 0))
				(self dispose:)
			)
		)
	)
)

(instance showLetter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 6 61 12 1 self)
			)
			(1
				(messager say: 6 61 12 2 self)
			)
			(2 (= seconds 6))
			(3
				(messager say: 6 61 12 3 self oneOnly: 0)
			)
			(4 (self dispose:))
		)
	)
)

(instance walkGuardOn of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(soundFx2 number: 702 loop: 1 play:)
				((ScriptID 80 5)
					init:
					loop: 3
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 336 172
					setMotion: MoveTo 314 150 self
				)
			)
			(1 (Face register ego self))
			(2 (messager say: 1 0 3 1 self))
			(3 (messager say: 1 0 3 2 self))
			(4
				((ScriptID 80 5) setScript: (ScriptID 80 4) self 1)
			)
			(5 (messager say: 1 0 3 3 self))
			(6
				(= temp0 (Max 160 (Min 185 ((ScriptID 80 5) y?))))
				((ScriptID 80 6)
					init:
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 336 172
					setSpeed: ((ScriptID 80 5) cycleSpeed?)
					setMotion: MoveTo 300 temp0 self
				)
			)
			(7
				((ScriptID 80 6)
					setMotion: PolyPath (+ ((ScriptID 80 5) x?) 10) (ego y?) self
				)
			)
			(8
				(messager say: 1 0 3 4 self oneOnly: 0)
			)
			(9 (= cycles 2))
			(10 (curRoom newRoom: 820))
		)
	)
)

(instance warnAboutGuard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 2 0 self))
			(1
				((ScriptID 80 0) guard2Timer: 6)
				(self dispose:)
			)
		)
	)
)

(instance cassima_door of Feature
	(properties
		x 44
		y 141
		z 21
		noun 6
		nsTop 100
		nsLeft 32
		nsBottom 141
		nsRight 57
		sightAngle 40
		approachX 35
		approachY 144
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 8 2)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 5 1)
			(super doVerb: theVerb &rest)
		else
			(switch theVerb
				(2
					(if ((ScriptID 80 0) tstFlag: 710 4)
						(if (== ((inventory at: 8) owner?) 870)
							(messager say: noun theVerb 15)
						else
							(messager say: noun theVerb 9)
						)
					else
						(theGame givePoints: 1)
						(theMusic fadeTo: 703 -1)
						(roomConv
							add: -1 noun theVerb 7 1
							add: -1 noun theVerb 7 2
							add: -1 noun theVerb 7 3
						)
						(if
						(not (OneOf ((inventory at: 39) owner?) 140 210))
							(roomConv
								add: -1 noun theVerb 7 4
								add: -1 noun theVerb 7 5
								add: -1 noun theVerb 7 6
							)
						else
							(roomConv
								add: -1 noun theVerb 8 1
								add: -1 noun theVerb 8 2
								add: -1 noun theVerb 8 3
							)
						)
						(roomConv
							add: -1 noun theVerb 7 7
							add: -1 noun theVerb 7 8
							add: -1 noun theVerb 7 9
							add: -1 noun theVerb 7 10
							add: -1 noun theVerb 7 11
							add: -1 noun theVerb 7 12
							add: -1 noun theVerb 7 13
							add: -1 noun theVerb 7 14
							init:
						)
					)
					((ScriptID 80 0) setFlag: 710 4)
				)
				(8
					(if ((ScriptID 80 0) tstFlag: 710 4)
						(bendOverToDoor next: giveDagger)
						(curRoom setScript: bendOverToDoor)
					else
						(messager say: noun 0 10 0)
					)
				)
				(64
					(messager say: noun theVerb)
				)
				(16
					(messager say: noun theVerb)
				)
				(61
					(cond 
						(((ScriptID 80 0) tstFlag: 710 8) (messager say: noun theVerb 14))
						(((ScriptID 80 0) tstFlag: 710 4)
							((ScriptID 80 0) setFlag: 710 8)
							(bendOverToDoor next: showLetter)
							(curRoom setScript: bendOverToDoor)
						)
						(else (messager say: noun 0 10 0))
					)
				)
				(else 
					(if ((ScriptID 80 0) tstFlag: 710 4)
						(messager say: noun 0 11 0)
					else
						(messager say: noun 0 10 0)
					)
				)
			)
		)
	)
)

(instance bench of Feature
	(properties
		y 1
		noun 11
		onMeCheck $0080
	)
)

(instance horse of Feature
	(properties
		y 1
		noun 12
		onMeCheck $0100
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
				(and
					(&
						$2002
						(= temp0 (OnControl 4 (event x?) (event y?)))
					)
					(= noun 9)
				)
			)
			((<= 0 (event y?)) (if (<= (event y?) 137) (= noun 10)))
		)
	)
)

(instance roomConv of Conversation
	(properties)
)
