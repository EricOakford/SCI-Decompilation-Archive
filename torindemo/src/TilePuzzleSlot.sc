;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20700)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Script)
(use PArray)
(use ExitFeature)
(use Plane)
(use Array)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	roPhace 0
)

(local
	local0
	local1
	local2
)
(procedure (localproc_0118 param1 &tmp temp0 temp1)
	(if (not local2)
		(Prints {TileVerbs not inited. 20700.sc djm})
		(return -1)
	)
	(= temp0 (local2 size:))
	(= temp1 0)
	(while (< temp1 temp0)
		(if (== param1 (local2 at: temp1)) (return temp1))
		(++ temp1)
	)
	(return -1)
)

(procedure (localproc_0185 &tmp temp0 temp1 temp2)
	(= temp2 (IntArray newWith: 9 2 1 0 5 4 3 8 7 6))
	(= temp1 1)
	(= temp0 0)
	(while (< temp0 9)
		(if
			(!=
				[gHoldTime temp0]
				(temp2 at: (if (!= [gLoop temp0] 0) (= temp1 0)))
			)
			(= temp1 0)
		)
		(++ temp0)
	)
	(temp2 dispose:)
	(= temp2 0)
	(if (not temp1) (return 0))
	((ScriptID 64017 0) set: 94)
	(return (curRoom setScript: soSolved))
)

(instance soSolved of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sound1 lThumbLoop: 20702 self)
			)
			(1 (messager say: 0 0 1 0 self))
			(2 (curRoom newRoom: 20800))
		)
	)
)

(instance soGoToChamber of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1 (curRoom newRoom: 20800))
		)
	)
)

(instance foToCliffExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					470
					265
					492
					229
					482
					176
					485
					146
					507
					109
					531
					84
					559
					72
					605
					49
					629
					20
					567
					20
					514
					20
					471
					11
					468
					0
					631
					0
					634
					78
					560
					119
					530
					153
					529
					197
					546
					278
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(ego setScript: soClimbUpTree)
	)
)

(instance foPhaceMS of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					1
					12
					49
					36
					120
					81
					181
					129
					254
					204
					331
					271
					332
					292
					165
					284
					165
					255
					156
					252
					150
					244
					143
					241
					140
					227
					120
					226
					0
					99
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 94)
			(curRoom newRoom: 20800)
		else
			(ego setScript: soClimbUpStairs)
		)
	)
)

(instance foExitCU of CUExitFeature
	(properties)
	
	(method (doVerb)
		(curRoom setScript: soClimbDownStairs)
	)
)

(class TilePuzzleSlot of Prop
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
		view 20712
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		holdTime -1
		reallyDispose -1
	)
	
	(method (init)
		(super init: &rest)
		(SetNowSeen self)
		(self setPri: 5)
		(self setVisibleRange: 27 32 24 30 31 48 29 25 28 26)
		(= holdTime [gHoldTime reallyDispose])
		(if (!= holdTime -1)
			(= loop [gLoop holdTime])
			(= view (local0 at: holdTime))
			(self setVisibleRange: 1)
		else
			(= loop 0)
			(= view 20712)
		)
		(= cel 0)
	)
	
	(method (handleEvent event &tmp theGVerb eventX eventY temp3 temp4)
		(if (not (self onMe: event)) (return 0))
		(if (== holdTime -1) (self setSpeedFraction: 0))
		(if (not (self setSpeedDirect: gVerb)) (return 0))
		(if (== gVerb 1)
			(= eventX (event x?))
			(= eventY (event y?))
			(= temp3 (Abs (- nsRight eventX)))
			(if
			(< (+ temp3 (= temp4 (Abs (- eventY nsTop)))) 40)
				(= theGVerb -2)
				(self setSpeedFraction: (ScriptID 64006 11))
			else
				(= temp3 (Abs (- eventX nsLeft)))
				(if
				(< (+ temp3 (= temp4 (Abs (- nsBottom eventY)))) 40)
					(= theGVerb -1)
					(self setSpeedFraction: (ScriptID 64006 12))
				else
					(= theGVerb 1)
					(self setSpeedFraction: 0)
				)
			)
		else
			(= theGVerb gVerb)
			(self setSpeedFraction: 0)
		)
		(return
			(if (== (event type?) evMOUSEBUTTON)
				(CueObj
					state: 0
					cycles: 0
					client: self
					theVerb: theGVerb
					changeState: 3
				)
				(event claimed: 1)
				(return 1)
			else
				(return (super handleEvent: event &rest))
			)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 27 32 24 30 31 48 25 28 26)
				(sound1 lThumbLoop: 20701)
				(gInventItem moveTo: 20700)
				(if (!= holdTime -1)
					((local1 at: holdTime) moveTo: -2)
					(self empty:)
				)
				(self audPlay: (localproc_0118 theVerb))
				(localproc_0185)
			)
			((== theVerb 29)
				(Prints
					{There is a funny noise and the tile will not go in}
				)
			)
			((== theVerb 1)
				(if (== holdTime -1)
					(MonoOut
						{attempt to pickup nonexistent tile. 20700.sc djm}
					)
					(SetDebug)
				)
				(sound1 lThumbLoop: 20701)
				((local1 at: holdTime) moveTo: -2)
				(self empty:)
			)
			((== theVerb -1) (self sfx: -1))
			((== theVerb -2) (self sfx: 1))
		)
	)
	
	(method (cue)
		(localproc_0185)
	)
	
	(method (empty)
		(= holdTime -1)
		(= view 20712)
		(= cel (= loop 0))
		(self setTotalWidth: 1)
		(= [gHoldTime reallyDispose] -1)
	)
	
	(method (audPlay theHoldTime)
		(= view (local0 at: (= holdTime theHoldTime)))
		(= loop [gLoop holdTime])
		(= [gHoldTime reallyDispose] holdTime)
		(self setVisibleRange: 1)
	)
	
	(method (sfx param1)
		(if (== holdTime -1)
			(Prints {Attempt to rotate empty slot. 20700.sc djm})
			(return)
		)
		(sound1 lThumbLoop: 20703)
		(if (== param1 1)
			(self setScript: (soRotLeft new:) self)
		else
			(self setScript: (soRotRight new:) self)
		)
	)
)

(instance soRotLeft of Script
	(properties)
	
	(method (changeState newState &tmp clientLoop)
		(switch (= state newState)
			(0
				(client setPri: 10)
				(if (== (= clientLoop (client loop?)) 0)
					(= clientLoop 3)
				else
					(= clientLoop (- clientLoop 1))
				)
				(client loop: clientLoop)
				(= [gLoop (client holdTime?)] clientLoop)
				(client cel: (client lastCel:))
				(= ticks (- (client cycleSpeed?) 1))
			)
			(1 (client setCycle: Beg self))
			(2
				(client setPri: 5)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance soRotRight of Script
	(properties)
	
	(method (changeState newState &tmp clientLoop)
		(switch (= state newState)
			(0
				(client setPri: 10)
				(client setCycle: End self)
			)
			(1
				(if (== (= clientLoop (client loop?)) 3)
					(= clientLoop 0)
				else
					(= clientLoop (+ clientLoop 1))
				)
				(client loop: clientLoop)
				(= [gLoop (client holdTime?)] clientLoop)
				(client cel: 0)
				(client setPri: 5)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance soClimbDownTree of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sound1 nHeight: 20118)
				(theGame handsOff:)
				(poTorin
					view: 20700
					loop: 0
					cel: 0
					posn: 434 307
					init:
					setCycle: CT 12 1 self
				)
			)
			(1
				(sound1 lThumbLoop: 20118)
				(poTorin setCycle: End self)
			)
			(2
				(poTorin dispose:)
				(ego posn: 434 307 oPanner: 1 -5436 5 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soClimbUpTree of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 496 309 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: 20700
					loop: 1
					cel: 0
					posn: 496 309
					init:
					setCycle: End self
				)
			)
			(3
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance soClimbUpStairs of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 325 320 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: 20700
					loop: 2
					cel: 0
					posn: 317 315
					init:
					setCycle: End self
				)
			)
			(3
				(poTorin dispose:)
				(curRoom initThumb: oPhaceCUPlane)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soClimbDownStairs of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom arrowDown: oPhaceCUPlane)
				(poTorin
					view: 20700
					loop: 3
					cel: 0
					posn: 234 297
					init:
					setCycle: End self
				)
			)
			(1
				(poTorin dispose:)
				(ego posn: 234 297 oPanner: 1 -5436 4 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oPhaceCUPlane of Plane
	(properties
		picture 20701
		priority 20
	)
	
	(method (init &tmp temp0 temp1 temp2 temp3 temp4)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(= temp0 0)
		(= temp2 38)
		(= temp3 0)
		(while (< temp3 3)
			(= temp1 261)
			(= temp4 0)
			(while (< temp4 3)
				((TilePuzzleSlot new:)
					reallyDispose: temp0
					x: temp1
					y: temp2
					init:
				)
				(= temp1 (+ temp1 54))
				(++ temp0)
				(++ temp4)
			)
			(= temp2 (+ temp2 54))
			(++ temp3)
		)
		(foExitCU init:)
		((ScriptID 64017 0) set: 93)
	)
)

(instance roPhace of TPRoom
	(properties
		picture 20700
	)
	
	(method (init)
		(super init: &rest)
		(= local0
			(PArray
				newWith: 9 20703 20704 20705 20706 20707 20708 20709 20710 20711
			)
		)
		(= local1
			(PArray
				newWith:
					9
					((ScriptID 64001 0) get: 17)
					((ScriptID 64001 0) get: 22)
					((ScriptID 64001 0) get: 14)
					((ScriptID 64001 0) get: 20)
					((ScriptID 64001 0) get: 21)
					((ScriptID 64001 0) get: 33)
					((ScriptID 64001 0) get: 15)
					((ScriptID 64001 0) get: 18)
					((ScriptID 64001 0) get: 16)
			)
		)
		(= local2 (PArray newWith: 9 27 32 24 30 31 48 25 28 26))
		(music1 pageSize: 20700)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 1 286 1 313 479 314 462 300
					yourself:
				)
		)
		(foPhaceMS init:)
		(foToCliffExit init:)
		(ego init: oPanner: hide:)
		(theGame handsOn:)
		(ego setScript: soClimbDownTree)
	)
	
	(method (dispose)
		(if local0 (local0 dispose:) (= local0 0))
		(if local1 (local1 dispose:) (= local1 0))
		(if local2 (local2 dispose:) (= local2 0))
		(super dispose: &rest)
	)
	
	(method (highlight)
		(if (== (curRoom plane?) oPhaceCUPlane)
			(super highlight: foExitCU)
		else
			(super highlight: &rest)
		)
	)
	
	(method (setWander)
		(return
			(if (== (curRoom plane?) oPhaceCUPlane)
				(return foExitCU)
			else
				(return foToCliffExit)
			)
		)
	)
	
	(method (zipTo)
		(cond 
			((!= (curRoom plane?) oPhaceCUPlane) (super zipTo: &rest))
			(
				(and
					(or
						(ego has: ((ScriptID 64001 0) get: 17))
						(!= -1 [gHoldTime (localproc_0118 27)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 22))
						(!= -1 [gHoldTime (localproc_0118 32)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 14))
						(!= -1 [gHoldTime (localproc_0118 24)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 20))
						(!= -1 [gHoldTime (localproc_0118 30)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 21))
						(!= -1 [gHoldTime (localproc_0118 31)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 33))
						(!= -1 [gHoldTime (localproc_0118 48)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 15))
						(!= -1 [gHoldTime (localproc_0118 25)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 18))
						(!= -1 [gHoldTime (localproc_0118 28)])
					)
					(or
						(ego has: ((ScriptID 64001 0) get: 16))
						(!= -1 [gHoldTime (localproc_0118 26)])
					)
				)
				(if
					(and
						(!= -1 [gHoldTime 0])
						(!= -1 [gHoldTime 1])
						(!= -1 [gHoldTime 2])
						(!= -1 [gHoldTime 3])
						(!= -1 [gHoldTime 4])
						(!= -1 [gHoldTime 5])
						(!= -1 [gHoldTime 6])
						(!= -1 [gHoldTime 7])
						(!= -1 [gHoldTime 8])
					)
					(if ((ScriptID 64017 0) test: 96)
						(if
							(Print
								font: 999
								fore: 0
								back: (Palette palUNSET_FLAG 127 127 127)
								addText: {Would you like to skip this puzzle?}
								addButton: 1 {Yes} 20 24
								addButton: 0 {No} 160 24
								init:
							)
							((ScriptID 64017 0) set: 94)
							(curRoom setScript: soGoToChamber)
						else
							(Prints
								{Sorry there's no other way I can help you on this puzzle.}
							)
						)
					else
						(Prints {You need to arrange these tiles to form a face})
						((ScriptID 64017 0) set: 96)
					)
				else
					(Prints {You need to put all nine tiles on this panel})
				)
			)
			(else
				(Prints
					{You need to find all nine tiles that belong here}
				)
				(super highlight: foExitCU)
			)
		)
	)
	
	(method (intoPouch)
		(if (!= -1 [gHoldTime 0])
			(Printf
				{Pos: %d %d %d %d %d %d %d %d %d Dir: %d %d %d %d %d %d %d %d %d}
				[gHoldTime 0]
				[gHoldTime 1]
				[gHoldTime 2]
				[gHoldTime 3]
				[gHoldTime 4]
				[gHoldTime 5]
				[gHoldTime 6]
				[gHoldTime 7]
				[gHoldTime 8]
				[gLoop 0]
				[gLoop 1]
				[gLoop 2]
				[gLoop 3]
				[gLoop 4]
				[gLoop 5]
				[gLoop 6]
				[gLoop 7]
				[gLoop 8]
			)
			(return)
		)
		(ego get: ((ScriptID 64001 0) get: 17))
		(ego get: ((ScriptID 64001 0) get: 22))
		(ego get: ((ScriptID 64001 0) get: 14))
		(ego get: ((ScriptID 64001 0) get: 20))
		(ego get: ((ScriptID 64001 0) get: 21))
		(ego get: ((ScriptID 64001 0) get: 19))
		(ego get: ((ScriptID 64001 0) get: 15))
		(ego get: ((ScriptID 64001 0) get: 18))
		(ego get: ((ScriptID 64001 0) get: 16))
		(ego get: ((ScriptID 64001 0) get: 33))
	)
)
