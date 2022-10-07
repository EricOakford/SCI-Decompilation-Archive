;;; Sierra Script 1.0 - (do not remove this comment)
(script# 860)
(include sci.sh)
(use Main)
(use CastleRoom)
(use NewFeature)
(use KQ6Print)
(use Kq6Procs)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use System)

(public
	rm860 0
)

(local
	local0
	local1 =  1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
)
(procedure (localproc_0cc9 param1 &tmp temp0 temp1 temp2 temp3)
	(if (> (ego y?) 137)
		(= temp1
			(if
			(>= ((ScriptID 80 5) y?) ((ScriptID 80 6) y?))
				(ScriptID 80 5)
			else
				(ScriptID 80 6)
			)
		)
	else
		(= temp1
			(if
			(<= ((ScriptID 80 5) y?) ((ScriptID 80 6) y?))
				(ScriptID 80 5)
			else
				(ScriptID 80 6)
			)
		)
	)
	(switch temp1
		((ScriptID 80 5)
			(= temp0 (ScriptID 80 6))
		)
		((ScriptID 80 6)
			(= temp0 (ScriptID 80 5))
		)
	)
	(if (< (ego y?) 137)
		(if (== temp0 (ScriptID 80 5))
			(= temp2 (>> local6 $0008))
		else
			(= temp2 (>> local7 $0008))
		)
	else
		(= temp2 (temp0 x?))
	)
	(= temp3 (temp1 y?))
	(temp0
		cycleSpeed: 5
		moveSpeed: 5
		setMotion: PolyPath temp2 temp3 param1
	)
)

(instance rm860 of CastleRoom
	(properties
		noun 2
		picture 860
		style $000a
		south 730
		vanishingX 198
		vanishingY 90
		minScaleSize 37
		minScaleY 111
		maxScaleY 177
		moveOtherGuard 1
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						141
						117
						170
						117
						113
						153
						128
						153
						116
						165
						98
						165
						75
						179
						63
						182
						45
						182
						36
						178
						13
						178
						0
						186
						0
						-10
						319
						-10
						319
						180
						221
						112
						141
						112
					yourself:
				)
		)
		(features
			add: eastDoors roomFeatures floor chairs hideFeature
			eachElementDo: #init
		)
		(super init: &rest)
		((ScriptID 80 0)
			guard1Code: guardsCode
			guard2Code: guardsCode
		)
		((ScriptID 1015 6) talkWidth: 150 x: 15 y: 20)
		((ScriptID 1015 7) talkWidth: 135 x: 160 y: 20)
		((ScriptID 80 5)
			setScript: (Clone guardPatrol) 0 1
			init:
			noun: 5
			actions: guardDoVerb
			okToCheck: guardCheck
			ignoreActors:
			sightAngle: 45
		)
		((ScriptID 80 6)
			setScript: guardPatrol
			init:
			noun: 5
			actions: guardDoVerb
			okToCheck: guardCheck
			ignoreActors:
			sightAngle: 45
		)
		((ScriptID 80 0) setupGuards:)
		(ego
			init:
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(switch prevRoomNum
			(870
				(ego posn: 173 114)
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0)
				(self setScript: getCaught)
			)
			(else 
				(ego loop: 1 setSpeed: 6 posn: 79 188)
				(self setScript: hideEgo)
			)
		)
		(= spotEgoScr captureEgo)
		(if (ego scaler?) ((ego scaler?) doit:))
		(if (not script) (theGame handsOn:))
		(soundFx2 number: 702 loop: 1 play:)
	)
	
	(method (doit)
		(= local0 (ego onControl: 1))
		(= local1
			(if (== (ego view?) 881) else (& local0 $0004))
		)
		(cond 
			(script 0)
			((InRect 0 0 165 117 ego) (curRoom newRoom: 870))
		)
		(super doit: &rest)
	)
)

(instance hideEgo of Script
	(properties)
	
	(method (init)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		((ScriptID 80 5) sightAngle: 180)
		((ScriptID 80 6) sightAngle: 180)
		(super init: &rest)
	)
	
	(method (doit)
		(if (and (ego mover?) (== state 4))
			(theGame handsOff:)
			(if (not local2)
				(= local2 ((User curEvent?) x?))
				(= local3 ((User curEvent?) y?))
				(= cycles 3)
			)
			(ego setMotion: 0)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 60 180 self)
			)
			(1 (ego setHeading: 135 self))
			(2
				(ego
					normal: 0
					view: 881
					setLoop: 3
					cel: 0
					x: 78
					y: 188
					setScale:
					scaleX: 142
					scaleY: 142
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(3
				(if (not ((ScriptID 80 0) tstFlag: 711 2048))
					((ScriptID 80 0) setFlag: 711 2048)
					(messager say: 1 0 6 0 self)
				else
					(messager say: 1 0 7 0 self)
				)
			)
			(4 (theGame handsOn:))
			(5
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				((ScriptID 80 5) sightAngle: 40)
				((ScriptID 80 6) sightAngle: 40)
				(ego setCycle: BegLoop self)
			)
			(6
				(ego
					reset: 5 900
					posn: (hideFeature approachX?) (hideFeature approachY?)
				)
				(if
					(and
						(CueObj client?)
						((CueObj client?) approachX?)
						(&
							((CueObj client?) _approachVerbs?)
							(approachCode doit: ((theIconBar curIcon?) message?))
						)
					)
					(if (== (CueObj client?) hideFeature)
						(= local8 1)
						((CueObj client?) doVerb: (CueObj theVerb?))
					else
						(ego
							setMotion:
								PolyPath
								((CueObj client?) approachX?)
								(+ (ego z?) ((CueObj client?) approachY?))
								CueObj
						)
					)
				else
					(ego setMotion: PolyPath local2 local3)
				)
				(theGame handsOn:)
				(= local2 (= local3 (= register 0)))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User canInput:)
				(not (event modifiers?))
				(== (theIconBar curIcon?) (theIconBar at: 3))
				(or
					((ScriptID 80 5) onMe: event)
					((ScriptID 80 6) onMe: event)
				)
			)
			(theGame handsOff:)
			(CueObj
				state: 0
				cycles: 0
				client: (ScriptID 80 5)
				theVerb: 2
			)
			((CueObj client?) approachX: 111 approachY: 172)
			((ScriptID 80 5) okToCheck: 0)
			((ScriptID 80 6) okToCheck: 0)
			(= local5 1)
			(messager say: 5 2 0 1 self)
			(= next talkToGuards)
			(event claimed: 1)
		)
		(event claimed?)
	)
)

(instance guardPatrol of Script
	(properties)
	
	(method (init &tmp [temp0 50])
		(= start -1)
		(super init: &rest)
		(if register
			(= register 0)
			(= register (| (& register $00ff) $c400))
			(= register (| (& register $ff00) $00b3))
			(client posn: (& register $00ff) 147)
			(= state 0)
		else
			(= register (| (& register $00ff) $ce00))
			(= register (| (& register $ff00) $00df))
			(client posn: (>> register $0008) 114)
		)
		(= cycles 1)
	)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo (& register $00ff) 147 self)
			)
			(1
				(client setMotion: MoveTo (>> register $0008) 114 self)
			)
			(2
				(client setHeading: 270 self)
			)
			(3 (= state -1) (= seconds 2))
		)
	)
)

(instance getCaught of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1 (messager say: 1 0 2 0 self))
			(2
				(curRoom spotEgo: (proc80_7))
			)
		)
	)
)

(instance captureEgo of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local6 (((ScriptID 80 5) script?) register?))
				(= local7 (((ScriptID 80 6) script?) register?))
				((ScriptID 80 5) setScript: 0 setMotion: 0 okToCheck: 0)
				((ScriptID 80 6) setScript: 0 setMotion: 0 okToCheck: 0)
				(Face register ego self)
			)
			(1 (messager say: 1 0 3 1 self))
			(2
				(cond 
					((not (ego facingMe: (ScriptID 80 5))) (Face (ScriptID 80 5) ego self))
					((not (ego facingMe: (ScriptID 80 6))) (Face (ScriptID 80 6) ego self))
					(else (= cycles 1))
				)
			)
			(3 (localproc_0cc9 self))
			(4 (= cycles 2))
			(5 (messager say: 1 0 3 2 self))
			(6 (messager say: 1 0 3 3 self))
			(7 (messager say: 1 0 3 4 self))
			(8
				((proc80_7) setScript: (ScriptID 80 4) self 1)
			)
			(9
				(messager say: 1 0 3 5 self oneOnly: 0)
			)
			(10 (curRoom newRoom: 820))
		)
	)
)

(instance talkToGuards of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0)
				((ScriptID 80 5) approachX: 0 approachY: 0)
				(if (not local5)
					(messager say: 5 2 0 1 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if local1
					(ego setMotion: PolyPath 111 172 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (not (ego facingMe: (ScriptID 80 5)))
					(Face (ScriptID 80 5) ego self)
				else
					(= cycles 1)
				)
			)
			(3 (= cycles 1))
			(4
				(messager say: 5 2 0 2 self oneOnly: 0)
			)
			(5
				(curRoom spotEgo: (proc80_7))
			)
		)
	)
)

(instance eastDoors of NewFeature
	(properties
		heading 270
		noun 3
		sightAngle 90
		onMeCheck $0002
		approachX 237
		approachY 139
		normal 0
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom spotEgo: (proc80_7))
			)
			(1
				(if (not local4)
					(++ local4)
					(messager say: noun theVerb 8)
				else
					(messager say: noun theVerb 9)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (doSpecial param1)
		(switch param1
			(5
				(messager say: noun param1 0 0 NewFeature)
			)
			(else  (super doSpecial:))
		)
	)
)

(instance chairs of Feature
	(properties
		noun 10
		onMeCheck $0008
	)
	
	(method (onMe event)
		(= x (event x?))
		(= y (event y?))
		(= sightAngle 26505)
		(super onMe: event)
	)
)

(instance hideFeature of Feature
	(properties
		nsTop 64
		nsBottom 176
		nsRight 73
		approachX 60
		approachY 180
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(cond 
					(local8 (= local8 0))
					((!= (curRoom script?) hideEgo) (curRoom setScript: hideEgo))
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance guardDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(cond 
			((== theVerb 2) (curRoom setScript: talkToGuards))
			((== theVerb 37)
				(KQ6Print posn: -1 10 say: 0 5 37 0 1 init:)
				(ego
					setMotion: PolyPath ((CueObj client?) x?) ((CueObj client?) y?)
				)
			)
			(else (= temp0 0))
		)
	)
)

(instance guardsCode of Code
	(properties)
	
	(method (doit)
		(return (if (not local1) (!= (curRoom script?) hideEgo) else 0))
	)
)

(instance guardCheck of Code
	(properties)
	
	(method (doit param1)
		(return
			(if
				(and
					(< 90 (param1 heading?))
					(< (param1 heading?) 270)
				)
			else
				(<= (ego y?) (param1 y?))
			)
		)
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
				(and
					(&
						$0200
						(= temp0 (OnControl 4 (event x?) (event y?)))
					)
					(= noun 6)
				)
			)
			((& $0100 temp0) (= noun 7))
		)
	)
)

(instance floor of NewFeature
	(properties
		noun 8
		onMeCheck $0080
	)
	
	(method (init)
		(super init: &rest)
		(= normal 0)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
				(cond 
					(
						(and
							(== (theIconBar curIcon?) (theIconBar useIconItem?))
							(== (theIconBar curInvIcon?) (inventory at: 27))
							(= _approachVerbs -32768)
							(= approachX 154)
							(= approachY 141)
						)
					)
					((not (= _approachVerbs 0)) (if (not (= approachX 0)) (not (= approachY 0))))
				)
			else
				0
			)
		)
	)
	
	(method (doSpecial param1)
		(switch param1
			(37
				(messager say: 8 37 0 0 self)
			)
			(else  (super doSpecial:))
		)
	)
)
