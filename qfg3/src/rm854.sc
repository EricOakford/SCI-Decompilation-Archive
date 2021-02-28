;;; Sierra Script 1.0 - (do not remove this comment)
(script# 854)
(include sci.sh)
(use Main)
(use EgoDead)
(use GloryControls)
(use JumpX)
(use IconBar)
(use Polygon)
(use Feature)
(use LoadMany)
(use Window)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm854 0
	thiefEndScript 1
	grapDeWiz 2
)

(local
	local0
	local1
	[local2 2]
)
(procedure (localproc_1628)
	(ropeControls state: (& (ropeControls state?) $ffdf))
)

(procedure (localproc_163e)
	(ropeControls disable: 0 1 2 3 4 5 6 eachElementDo: #show)
)

(procedure (localproc_165a)
	(ropeControls enable: 0 1 2 eachElementDo: #show)
)

(procedure (localproc_166e &tmp temp0)
)

(instance rm854 of Rm
	(properties
		modNum 850
		noun 18
		picture 850
	)
	
	(method (init)
		(= heroType 2)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 21 76 43 76 43 70 21 72 21 75
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 118 35 118 39 146 37 137 35 118 36
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 288 64 317 68 317 59 289 62 290 64
					yourself:
				)
		)
		(self setRegions: 850)
		(pillarA init: setPri: 1 stopUpd:)
		(pillarB init:)
		(pillarC init:)
		((ScriptID 850 4) x: 219 y: 81 z: 0)
		((ScriptID 850 6) init: stopUpd:)
		(self setScript: thiefEndScript)
		(theGame save: 1)
	)
	
	(method (doit)
		(cond 
			(
			(and (GameIsRestarting) (> local0 2) (< local0 4))
				(Graph grDRAW_LINE 71 38 35 125 190 -1 -1)
				(Graph grUPDATE_BOX 35 38 71 125 1)
			)
			((and (GameIsRestarting) (> local0 3))
				(Graph grDRAW_LINE 75 42 31 128 190 -1 -1)
				(Graph grUPDATE_BOX 31 42 75 128 1)
				(Graph grDRAW_LINE 37 131 64 298 190 -1 -1)
				(Graph grUPDATE_BOX 37 131 64 298 1)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany 0 57)
		(super dispose:)
	)
)

(instance thiefEndScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: 6)
				(ego changeGait: 2)
				(= seconds 1)
			)
			(1
				(ego code: egoGaitCheck setMotion: MoveTo 20 183 self)
			)
			(2
				(messager say: 1 6 4 0 self 850)
			)
			(3 (= seconds 5))
			(4 (self dispose:))
		)
	)
)

(instance climbPillarA of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
				(== ((ScriptID 850 2) script?) (ScriptID 850 8))
					((ScriptID 850 8) dispose:)
				)
				(if (cast contains: (ScriptID 850 9))
					((ScriptID 850 9) dispose:)
				)
				(ego setMotion: MoveTo 32 115 self)
			)
			(1
				(ego
					view: 7
					loop: 3
					setPri: 12
					setCycle: Fwd
					setMotion: MoveTo 33 106 self
				)
			)
			(2
				(ego
					x: 28
					y: 72
					loop: 5
					cel: 0
					setPri: 12
					setCycle: End self
				)
			)
			(3
				(= local0 1)
				(ego normalize: 0)
				(HandsOn)
				(theIconBar disable: 6 5)
				(self dispose:)
			)
		)
	)
)

(instance grapPillarB of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: 34
					y: 71
					view: 8
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(1
				((ScriptID 850 17) number: 721 play:)
				(grapThingy
					x: 58
					y: 32
					init:
					setCycle: Fwd
					setMotion: JumpTo 128 31 self
				)
			)
			(2
				((ScriptID 850 17) number: 361 play: 127)
				(grapThingy dispose:)
				(ego normalize:)
				(Graph grDRAW_LINE 71 38 35 125 190 -1 -1)
				(Graph grUPDATE_BOX 35 38 71 125 1)
				(= local0 2)
				(walkHandler addToFront: pillarB)
				(HandsOn)
				(theIconBar disable: 5 6)
				(self dispose:)
			)
		)
	)
)

(instance grapPillarC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: Walk setMotion: MoveTo 132 35 self)
			)
			(1
				(ego view: 8 loop: 4 cel: 0 setCycle: End self)
			)
			(2
				((ScriptID 850 17) number: 721 play:)
				(grapThingy
					x: 157
					y: 9
					setPri: 14
					setCycle: Fwd
					init:
					setMotion: JumpTo 310 57 self
				)
			)
			(3
				((ScriptID 850 17) number: 361 play: 127)
				(= local0 4)
				(grapThingy dispose:)
				(Graph grDRAW_LINE 37 131 64 298 148 -1 -1)
				(Graph grUPDATE_BOX 37 131 64 298 1)
				(ego normalize:)
				(= cycles 2)
			)
			(4
				(messager say: 3 6 61 0 self 850)
			)
			(5
				((ScriptID 850 2) setCycle: End self)
			)
			(6
				(ropeFire init: setCycle: Fwd setScript: ropeIsBurning)
				(walkHandler addToFront: pillarC)
				(HandsOn)
				(theIconBar disable: 5 6)
				(self dispose:)
			)
		)
	)
)

(instance ropeIsBurning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 30))
			(1
				(ego setScript: egoDeathFall)
			)
		)
	)
)

(instance toPillarB of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego normalize: 0 setLoop: 0 setMotion: MoveTo 38 71 self)
			)
			(1
				(ego
					view: 11
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 128 36 self
				)
			)
			(2
				(if (Btst 124)
					(self cue:)
				else
					(messager say: 1 6 35 0 self 850)
				)
			)
			(3
				(if (Btst 124)
					(self cue:)
				else
					((ScriptID 850 6) dispose:)
					(DrawPic 850 dpOPEN_CHECKBOARD)
					(= seconds 2)
				)
			)
			(4
				(if (Btst 124)
					(self cue:)
				else
					(messager say: 3 6 6 0 self 850)
				)
			)
			(5
				(Bset 124)
				(walkHandler delete: pillarB)
				(HandsOn)
				(theIconBar disable: 6 5)
				(= local0 3)
				(ego normalize: 0)
				((ScriptID 850 2) setScript: timeToBurn)
				(self dispose:)
			)
		)
	)
)

(instance timeToBurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 850 2) view: 861 loop: 1 setCycle: End self)
			)
			(1
				((ScriptID 850 2)
					view: 863
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(2
				((ScriptID 850 17) number: 101 play:)
				(pillarFire init: setCycle: End self)
			)
			(3
				(pillarFire loop: 1 setCycle: Fwd)
				(= seconds 10)
			)
			(4
				(ego
					takeDamage:
					(switch arcadeDifficulty
						(1 2)
						(2 4)
						(3 6)
					)
				)
				(-- state)
				(= seconds 1)
			)
		)
	)
)

(instance toPillarC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 131 37 self ignoreHorizon: 1)
			)
			(1
				(ego
					view: 11
					setLoop: 2
					setPri: 14
					setCycle: Walk
					setMotion: MoveTo 193 47 self
				)
			)
			(2
				(ego
					view: 30
					setLoop: 2
					cel: 0
					setMotion: JumpX 236 55 15 self
					setCycle: JumpCycler 3 8
				)
			)
			(3
				(ego
					view: 11
					setCycle: Walk
					setMotion: MoveTo 300 64 self
				)
			)
			(4
				(messager say: 3 6 48 0 self 850)
			)
			(5
				((ScriptID 850 2) view: 863 setCycle: End self)
			)
			(6
				(globalSound number: 101 play: 127)
				(pillarFire
					x: 315
					y: 138
					loop: 0
					cel: 0
					setPri: 14
					init:
					setCycle: End self
				)
				(ego normalize:)
				(HandsOn)
				(ropeIsBurning dispose:)
				(= local0 5)
				(walkHandler delete: pillarC)
				(pillarC dispose:)
				(theIconBar disable: 5 6)
			)
			(7
				(pillarFire loop: 1 setCycle: Fwd)
				(= seconds 15)
			)
			(8 (EgoDead 18 850 857 End))
		)
	)
)

(instance arcadeCrossing of Script
	(properties)
	
	(method (dispose)
		(ego normalize:)
		(HandsOn)
		(theIconBar disable: 6 5)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 11 setLoop: 2 setCycle: Walk)
				(ropeControls init: show: dispose:)
			)
		)
	)
)

(instance rightStep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) 5) (+ (ego y?) 1) self
				)
			)
			(1
				(if (> (ego x?) 297)
					(ego setMotion: MoveTo 299 (- (ego y?) 4))
					(ropeControls hide:)
					(arcadeCrossing dispose:)
					(walkHandler delete: pillarC)
					(pillarC dispose:)
					(ego normalize:)
					(self dispose:)
				else
					(self cue:)
				)
			)
			(2
				(localproc_165a)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance leftStep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) 5) (- (ego y?) 1) self
				)
			)
			(1
				(if (< (ego x?) 131)
					(ropeControls hide:)
					(arcadeCrossing dispose:)
					(ego normalize:)
					(self dispose:)
				else
					(self cue:)
				)
			)
			(2
				(localproc_165a)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance jumpRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 193 47 self)
			)
			(1
				(ego
					view: 30
					setLoop: 2
					cel: 0
					setMotion: JumpX 236 55 15 self
					setCycle: JumpCycler 3 8
				)
			)
			(2
				(if (<= (ego trySkill: 2 175) 0)
					(curRoom setScript: egoDeathFall)
				else
					(self cue:)
				)
			)
			(3
				(ropeControls hide:)
				(walkHandler delete: pillarC)
				(ego
					view: 11
					setCycle: Walk
					setMotion: MoveTo 300 64 self
				)
			)
			(4
				(messager say: 3 6 48 0 self 850)
			)
			(5
				((ScriptID 850 2) view: 863 setCycle: End self)
			)
			(6
				(globalSound number: 101 play: 127)
				(pillarFire
					x: 315
					y: 138
					loop: 0
					cel: 0
					setPri: 14
					init:
					setCycle: End self
				)
				(ego normalize:)
				(HandsOn)
				(ropeIsBurning dispose:)
				(= local0 5)
				(arcadeCrossing dispose:)
				(theIconBar disable: 5 6)
			)
			(7
				(pillarFire loop: 1 setCycle: Fwd)
				(= seconds 15)
			)
			(8 (EgoDead 18 850 857 End))
		)
	)
)

(instance egoDeathFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego script?) ((ego script?) dispose:))
				(ropeControls hide:)
				(globalSound number: 912 play: 127)
				((ScriptID 850 2) ignoreActors: 1)
				((ScriptID 850 18) ignoreActors: 1)
				((ScriptID 850 4) ignoreActors: 1)
				(ego
					view: 6
					setLoop: 3
					setStep: 5 5
					setCycle: End
					ignoreActors: 1
					setMotion: MoveTo (ego x?) (+ (ego y?) 50) self
				)
			)
			(1 (EgoDead 39 850))
		)
	)
)

(instance grapDeWiz of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(toPillarC dispose:)
				(ego setMotion: MoveTo 299 65 self)
			)
			(1
				(ego view: 8 setLoop: 1 setCycle: End self)
			)
			(2
				(globalSound number: 721 play: 127)
				(grapThingy
					x: 283
					y: 29
					setCycle: Fwd
					setPri: 14
					init:
					xStep: 8
					yStep: 7
					setMotion: JumpTo 185 76 self
				)
			)
			(3
				(messager say: 2 6 41 0 self 850)
			)
			(4
				(ropeFire dispose:)
				(pillarFire dispose:)
				(grapThingy dispose:)
				(globalSound number: 931 play: 127)
				((ScriptID 850 2)
					view: 868
					setPri: 14
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(5
				((ScriptID 850 2) loop: 1 cel: 0 setCycle: End)
				((ScriptID 850 18) setCycle: End)
				((ScriptID 850 4)
					setLoop: 1
					setMotion: MoveTo 260 61 self
				)
			)
			(6
				((ScriptID 850 18) setCycle: Beg)
				(ego solvePuzzle: 340 10 solvePuzzle: 341 20 normalize:)
				((ScriptID 850 2) dispose:)
				((ScriptID 850 4) dispose:)
				((ScriptID 850 5) setCycle: End self)
			)
			(7
				(globalSound number: 831 play: 127)
				((ScriptID 850 5) setLoop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(8
				((ScriptID 850 5) dispose:)
				(messager say: 2 6 3 0 self 850)
			)
			(9 (curRoom newRoom: 830))
		)
	)
)

(instance wizBlast of Actor
	(properties
		yStep 7
		view 21
		loop 3
		signal $4000
		xStep 8
	)
)

(instance ropeFire of Prop
	(properties
		x 214
		y 54
		view 870
		loop 2
		signal $4000
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (== (ego script?) jumpRope))
				(== arcadeDifficulty 3)
				(self onMe: ego)
			)
			(ropeControls hide:)
			(curRoom setScript: egoDeathFall)
		)
	)
)

(instance pillarA of View
	(properties
		x 28
		y 115
		noun 6
		view 854
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (not (curRoom script?)) (not local0))
					(curRoom setScript: climbPillarA)
				)
			)
			(16
				(if ((inventory at: 6) state?)
					(messager say: 6 16 0 0 0 850)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pillarB of Feature
	(properties
		x 133
		y 64
		noun 8
		nsTop 31
		nsLeft 114
		nsBottom 91
		nsRight 152
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(16
				(if
					(and
						((inventory at: 6) state?)
						(== local0 1)
						(not (curRoom script?))
					)
					(curRoom setScript: grapPillarB)
				else
					(messager say: 7 16 0 0 0 850)
				)
			)
			(3
				(pillarB doVerb: 4)
				((User curEvent?) claimed: 1)
			)
			(4
				(if (and (== local0 2) (not (curRoom script?)))
					(curRoom setScript: toPillarB)
				)
			)
		)
	)
)

(instance pillarC of Feature
	(properties
		x 304
		y 80
		noun 7
		nsTop 58
		nsLeft 283
		nsBottom 96
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(16
				(if
					(and
						((inventory at: 6) state?)
						(== local0 3)
						(not (curRoom script?))
					)
					(curRoom setScript: grapPillarC)
				)
			)
			(3
				(if (and (== local0 4) (not (curRoom script?)))
					(if (cast contains: pillarFire)
						(timeToBurn dispose:)
						(pillarFire dispose:)
					)
					(if (== arcadeDifficulty 3)
						(curRoom setScript: arcadeCrossing)
					else
						(curRoom setScript: toPillarC)
					)
					((User curEvent?) claimed: 1)
				)
			)
			(4
				(cond 
					((and (== local0 4) (not (curRoom script?)))
						(if (== arcadeDifficulty 3)
							(curRoom setScript: arcadeCrossing)
						else
							(curRoom setScript: toPillarC)
						)
					)
					((== local0 5) (messager say: 2 6 38 0 0 850))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance grapThingy of Actor
	(properties
		view 21
		loop 6
		signal $4000
	)
)

(instance pillarFire of Prop
	(properties
		x 133
		y 88
		view 870
		signal $4000
	)
)

(instance ropeControls of GloryControls
	(properties)
	
	(method (init)
		(super init: &rest)
		(theGame setCursor: 999)
		(= icon1 iconJump)
		(= icon2 iconRight)
		(= icon3 iconLeft)
		(= icon4 iconPush)
		(= icon5 iconPull)
		(= icon6 iconDrop)
		(= icon7 iconQuit)
		(self
			add:
				iconJump
				iconRight
				iconLeft
				iconPush
				iconPull
				iconDrop
				iconQuit
				iconMiddle
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #cursor 999
			eachElementDo: #signal 129
		)
		(self disable: iconPush iconPull iconQuit iconDrop)
		(iconQuit signal: (| (iconQuit signal?) $0040))
		(self add: iconDummy)
		(SetCursor 140 105 200 187)
	)
	
	(method (show)
		(= window bridgeWin)
		(User input: 1)
		(= local1 (Graph grSAVE_BOX 129 104 189 200 1))
		(super show: &rest)
		(Graph grRESTORE_BOX local1)
		(Graph grUPDATE_BOX 130 105 189 200 1)
	)
	
	(method (hide)
		(SetCursor -2)
		(super hide:)
	)
	
	(method (dispatchEvent event)
		(localproc_166e)
		(= gameTime (GetTime))
		(timers eachElementDo: #doit)
		(cast eachElementDo: #doit)
		((curRoom script?) doit:)
		(Animate (cast elements?) 1)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
		(super dispatchEvent: event)
	)
)

(instance iconJump of IconI
	(properties
		view 470
		loop 1
		cel 0
		nsLeft 2
		nsTop 2
		maskView 470
		maskLoop 10
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_163e)
				(ego setScript: jumpRope)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconMiddle of IconI
	(properties
		view 470
		loop 7
		cel 0
		nsLeft 24
		nsTop 2
	)
)

(instance iconRight of IconI
	(properties
		view 470
		loop 9
		cel 0
		nsLeft 60
		nsTop 12
		maskView 470
		maskLoop 10
		maskCel 5
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_163e)
				(ego setScript: rightStep)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconLeft of IconI
	(properties
		view 470
		loop 8
		cel 0
		nsLeft 3
		nsTop 12
		maskView 470
		maskLoop 10
		maskCel 4
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_163e)
				(ego setScript: leftStep)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconPush of IconI
	(properties
		view 470
		loop 2
		cel 0
		nsLeft 50
		nsTop 2
		maskView 470
		maskLoop 10
		maskCel 1
	)
	
	(method (select)
		(return (if (super select: &rest) (return 1) else 0))
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconPull of IconI
	(properties
		view 470
		loop 3
		cel 0
		nsLeft 2
		nsTop 34
		maskView 470
		maskLoop 10
		maskCel 2
	)
	
	(method (select)
		(return (if (super select: &rest) (return 1) else 0))
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconDrop of IconI
	(properties
		view 470
		loop 4
		cel 0
		nsLeft 53
		nsTop 34
		maskView 470
		maskLoop 10
		maskCel 3
	)
	
	(method (select)
		(return (if (super select: &rest) (return 1) else 0))
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconQuit of IconI
	(properties
		view 470
		loop 5
		cel 0
		nsLeft 24
		nsTop 24
		maskView 470
		maskLoop 10
		maskCel 6
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_1628)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal $0020)) (return))
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconDummy of IconI
	(properties
		nsLeft 20
		nsTop 20
		nsRight 21
		nsBottom 21
		cursor 997
		signal $0080
	)
	
	(method (show)
	)
	
	(method (select)
		(return 1)
	)
	
	(method (highlight)
	)
)

(instance bridgeWin of Window
	(properties
		top 130
		left 105
		bottom 189
		right 200
		type $0083
	)
	
	(method (open)
		(super open: &rest)
		(DrawCel 470 8 0 0 0 -1)
		(DrawCel 470 0 0 0 0 -1)
	)
)

(instance egoGaitCheck of Code
	(properties)
	
	(method (doit)
		(if (and (not (Btst 124)) (not (== egoGait 2)))
			(curRoom setScript: (ScriptID 850 19))
		)
		(super doit: &rest)
	)
)
