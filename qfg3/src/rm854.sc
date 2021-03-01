;;; Sierra Script 1.0 - (do not remove this comment)
(script# 854)
(include game.sh) (include "850.shm")
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
	onPillar
	saveBits
	[local2 2]
)
(procedure (localproc_1628)
	(ropeControls state: (& (ropeControls state?) $ffdf))
)

(procedure (DisableRopeControls)
	(ropeControls disable: 0 1 2 3 4 5 6 eachElementDo: #show)
)

(procedure (EnableRopeControls)
	(ropeControls enable: 0 1 2 eachElementDo: #show)
)

(procedure (localproc_166e &tmp temp0)
)

(instance rm854 of Room
	(properties
		modNum 850
		noun N_ROOM
		picture 850
	)
	
	(method (init)
		(= heroType THIEF)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						21 76
						43 76
						43 70
						21 72
						21 75
					yourself:
				)
				((Polygon new:)
					type: PContainedAccess
					init:
						118 35
						118 39
						146 37
						137 35
						118 36
					yourself:
				)
				((Polygon new:)
					type: PContainedAccess
					init:
						288 64
						317 68
						317 59
						289 62
						290 64
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
			((and (GameIsRestarting) (> onPillar 2) (< onPillar 4))
				(Graph GDrawLine 71 38 35 125 190 -1 -1)
				(Graph GShowBits 35 38 71 125 1)
			)
			((and (GameIsRestarting) (> onPillar 3))
				(Graph GDrawLine 75 42 31 128 190 -1 -1)
				(Graph GShowBits 31 42 75 128 1)
				(Graph GDrawLine 37 131 64 298 190 -1 -1)
				(Graph GShowBits 37 131 64 298 1)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany FALSE JUMPX)
		(super dispose:)
	)
)

(instance thiefEndScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: ICON_CAST)
				(ego changeGait: MOVE_SNEAK)
				(= seconds 1)
			)
			(1
				(ego code: egoGaitCheck setMotion: MoveTo 20 183 self)
			)
			(2
				(messager say: N_DELORD V_DOIT C_DEMONS_TALK 0 self 850)
			)
			(3
				(= seconds 5)
			)
			(4
				(self dispose:)
			)
		)
	)
)

(instance climbPillarA of Script
	
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
					setCycle: Forward
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
					setCycle: EndLoop self
				)
			)
			(3
				(= onPillar 1)
				(ego normalize: 0)
				(HandsOn)
				(theIconBar disable: ICON_CAST ICON_ACTIONS)
				(self dispose:)
			)
		)
	)
)

(instance grapPillarB of Script
	
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
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 850 17) number: 721 play:)
				(grapThingy
					x: 58
					y: 32
					init:
					setCycle: Forward
					setMotion: JumpTo 128 31 self
				)
			)
			(2
				((ScriptID 850 17) number: 361 play: 127)
				(grapThingy dispose:)
				(ego normalize:)
				(Graph GDrawLine 71 38 35 125 190 -1 -1)
				(Graph GShowBits 35 38 71 125 1)
				(= onPillar 2)
				(walkHandler addToFront: pillarB)
				(HandsOn)
				(theIconBar disable: ICON_ACTIONS ICON_CAST)
				(self dispose:)
			)
		)
	)
)

(instance grapPillarC of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: Walk setMotion: MoveTo 132 35 self)
			)
			(1
				(ego view: 8 loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2
				((ScriptID 850 17) number: 721 play:)
				(grapThingy
					x: 157
					y: 9
					setPri: 14
					setCycle: Forward
					init:
					setMotion: JumpTo 310 57 self
				)
			)
			(3
				((ScriptID 850 17) number: 361 play: 127)
				(= onPillar 4)
				(grapThingy dispose:)
				(Graph GDrawLine 37 131 64 298 148 -1 -1)
				(Graph GShowBits 37 131 64 298 1)
				(ego normalize:)
				(= cycles 2)
			)
			(4
				(messager say: N_DEWIZ V_DOIT C_BURN_ROPE 0 self 850)
			)
			(5
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(6
				(ropeFire init: setCycle: Forward setScript: ropeIsBurning)
				(walkHandler addToFront: pillarC)
				(HandsOn)
				(theIconBar disable: ICON_ACTIONS ICON_CAST)
				(self dispose:)
			)
		)
	)
)

(instance ropeIsBurning of Script
	
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
				(if (Btst fWizNoticesEgo)
					(self cue:)
				else
					(messager say: N_DELORD V_DOIT C_NOTICE_THIEF 0 self 850)
				)
			)
			(3
				(if (Btst fWizNoticesEgo)
					(self cue:)
				else
					((ScriptID 850 6) dispose:)
					(DrawPic 850 DISSOLVE)
					(= seconds 2)
				)
			)
			(4
				(if (Btst fWizNoticesEgo)
					(self cue:)
				else
					(messager say: N_DEWIZ V_DOIT C_NOTICE_EGO2 0 self 850)
				)
			)
			(5
				(Bset fWizNoticesEgo)
				(walkHandler delete: pillarB)
				(HandsOn)
				(theIconBar disable: ICON_CAST ICON_ACTIONS)
				(= onPillar 3)
				(ego normalize: 0)
				((ScriptID 850 2) setScript: timeToBurn)
				(self dispose:)
			)
		)
	)
)

(instance timeToBurn of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 850 2) view: 861 loop: 1 setCycle: EndLoop self)
			)
			(1
				((ScriptID 850 2)
					view: 863
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 850 17) number: 101 play:)
				(pillarFire init: setCycle: EndLoop self)
			)
			(3
				(pillarFire loop: 1 setCycle: Forward)
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
				(messager say: N_DEWIZ V_DOIT C_JUMP_ROPE 0 self 850)
			)
			(5
				((ScriptID 850 2) view: 863 setCycle: EndLoop self)
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
					setCycle: EndLoop self
				)
				(ego normalize:)
				(HandsOn)
				(ropeIsBurning dispose:)
				(= onPillar 5)
				(walkHandler delete: pillarC)
				(pillarC dispose:)
				(theIconBar disable: 5 6)
			)
			(7
				(pillarFire loop: 1 setCycle: Forward)
				(= seconds 15)
			)
			(8
				(EgoDead C_DEATH_FIRE 850 857 EndLoop)
			)
		)
	)
)

(instance arcadeCrossing of Script
	
	(method (dispose)
		(ego normalize:)
		(HandsOn)
		(theIconBar disable: ICON_CAST ICON_ACTIONS)
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
				(EnableRopeControls)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance leftStep of Script

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
				(EnableRopeControls)
				(user canInput: 1)
				(self dispose:)
			)
		)
	)
)

(instance jumpRope of Script
	
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
				(if (<= (ego trySkill: AGIL 175) 0)
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
				(messager say: N_DEWIZ V_DOIT C_JUMP_ROPE 0 self 850)
			)
			(5
				((ScriptID 850 2) view: 863 setCycle: EndLoop self)
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
					setCycle: EndLoop self
				)
				(ego normalize:)
				(HandsOn)
				(ropeIsBurning dispose:)
				(= onPillar 5)
				(arcadeCrossing dispose:)
				(theIconBar disable: ICON_ACTIONS ICON_CAST)
			)
			(7
				(pillarFire loop: 1 setCycle: Forward)
				(= seconds 15)
			)
			(8
				(EgoDead C_DEATH_FIRE 850 857 EndLoop)
			)
		)
	)
)

(instance egoDeathFall of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego script?)
					((ego script?) dispose:)
				)
				(ropeControls hide:)
				(globalSound number: 912 play: 127)
				((ScriptID 850 2) ignoreActors: TRUE)
				((ScriptID 850 18) ignoreActors: TRUE)
				((ScriptID 850 4) ignoreActors: TRUE)
				(ego
					view: 6
					setLoop: 3
					setStep: 5 5
					setCycle: EndLoop
					ignoreActors: TRUE
					setMotion: MoveTo (ego x?) (+ (ego y?) 50) self
				)
			)
			(1
				(EgoDead C_DEATH_FALL 850)
			)
		)
	)
)

(instance grapDeWiz of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(toPillarC dispose:)
				(ego setMotion: MoveTo 299 65 self)
			)
			(1
				(ego view: 8 setLoop: 1 setCycle: EndLoop self)
			)
			(2
				(globalSound number: 721 play: 127)
				(grapThingy
					x: 283
					y: 29
					setCycle: Forward
					setPri: 14
					init:
					xStep: 8
					yStep: 7
					setMotion: JumpTo 185 76 self
				)
			)
			(3
				(messager say: N_DEWIZ V_DOIT C_GRAPNEL_WIZ 0 self 850)
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
					setCycle: EndLoop self
				)
			)
			(5
				((ScriptID 850 2) loop: 1 cel: 0 setCycle: EndLoop)
				((ScriptID 850 18) setCycle: EndLoop)
				((ScriptID 850 4)
					setLoop: 1
					setMotion: MoveTo 260 61 self
				)
			)
			(6
				((ScriptID 850 18) setCycle: BegLoop)
				(ego
					solvePuzzle: fBeatDeWiz 10
					solvePuzzle: fKnockedOrb 20
					normalize:
				)
				((ScriptID 850 2) dispose:)
				((ScriptID 850 4) dispose:)
				((ScriptID 850 5) setCycle: EndLoop self)
			)
			(7
				(globalSound number: 831 play: 127)
				((ScriptID 850 5) setLoop: 1 setCycle: Forward)
				(= seconds 3)
			)
			(8
				((ScriptID 850 5) dispose:)
				(messager say: N_ROOM V_DOIT C_EXIT 0 self 850)
			)
			(9
				(curRoom newRoom: 830)
			)
		)
	)
)

(instance wizBlast of Actor
	(properties
		yStep 7
		view 21
		loop 3
		signal ignrAct
		xStep 8
	)
)

(instance ropeFire of Prop
	(properties
		x 214
		y 54
		view 870
		loop 2
		signal ignrAct
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
		noun N_PILLAR_A
		view 854
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (and (not (curRoom script?)) (not onPillar))
					(curRoom setScript: climbPillarA)
				)
			)
			(V_GRAPNEL
				(if ((inventory at: iGrapnel) state?)
					(messager say: N_PILLAR_A V_GRAPNEL NULL 0 0 850)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance pillarB of Feature
	(properties
		x 133
		y 64
		noun N_PILLAR_B
		nsTop 31
		nsLeft 114
		nsBottom 91
		nsRight 152
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_GRAPNEL
				(if
					(and
						((inventory at: 6) state?)
						(== onPillar 1)
						(not (curRoom script?))
					)
					(curRoom setScript: grapPillarB)
				else
					(messager say: N_PILLAR_C V_GRAPNEL NULL 0 0 850)
				)
			)
			(3
				(pillarB doVerb: V_DO)
				((User curEvent?) claimed: TRUE)
			)
			(4
				(if (and (== onPillar 2) (not (curRoom script?)))
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
		noun N_PILLAR_C
		nsTop 58
		nsLeft 283
		nsBottom 96
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_GRAPNEL
				(if
					(and
						((inventory at: iGrapnel) state?)
						(== onPillar 3)
						(not (curRoom script?))
					)
					(curRoom setScript: grapPillarC)
				)
			)
			(V_WALK
				(if (and (== onPillar 4) (not (curRoom script?)))
					(if (cast contains: pillarFire)
						(timeToBurn dispose:)
						(pillarFire dispose:)
					)
					(if (== arcadeDifficulty 3)
						(curRoom setScript: arcadeCrossing)
					else
						(curRoom setScript: toPillarC)
					)
					((User curEvent?) claimed: TRUE)
				)
			)
			(V_DO
				(cond 
					((and (== onPillar 4) (not (curRoom script?)))
						(if (== arcadeDifficulty 3)
							(curRoom setScript: arcadeCrossing)
						else
							(curRoom setScript: toPillarC)
						)
					)
					((== onPillar 5)
						(messager say: N_ROOM V_DOIT C_CANT_GO_DOWN 0 0 850)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance grapThingy of Actor
	(properties
		view 21
		loop 6
		signal ignrAct
	)
)

(instance pillarFire of Prop
	(properties
		x 133
		y 88
		view 870
		signal ignrAct
	)
)

(instance ropeControls of GloryControls
	
	(method (init)
		(super init: &rest)
		(theGame setCursor: ARROW_CURSOR)
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
		(iconQuit signal: (| (iconQuit signal?) HIDEBAR))
		(self add: iconDummy)
		(SetCursor 140 105 200 187)
	)
	
	(method (show)
		(= window bridgeWin)
		(User input: TRUE)
		(= saveBits (Graph GSaveBits 129 104 189 200 1))
		(super show: &rest)
		(Graph GRestoreBits saveBits)
		(Graph GShowBits 130 105 189 200 1)
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
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue)
		)
		(super dispatchEvent: event)
	)
)

(instance iconJump of IconItem
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
				(DisableRopeControls)
				(ego setScript: jumpRope)
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconMiddle of IconItem
	(properties
		view 470
		loop 7
		cel 0
		nsLeft 24
		nsTop 2
	)
)

(instance iconRight of IconItem
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
				(DisableRopeControls)
				(ego setScript: rightStep)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconLeft of IconItem
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
				(DisableRopeControls)
				(ego setScript: leftStep)
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconPush of IconItem
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
		(return
			(if (super select: &rest)
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconPull of IconItem
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
		(return
			(if (super select: &rest)
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconDrop of IconItem
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
		(return
			(if (super select: &rest)
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconQuit of IconItem
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
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
)

(instance iconDummy of IconItem
	(properties
		nsLeft 20
		nsTop 20
		nsRight 21
		nsBottom 21
		cursor 997
		signal FIXED_POSN
	)
	
	(method (show)
	)
	
	(method (select)
		(return TRUE)
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
	
	(method (doit)
		(if (and (not (Btst fWizNoticesEgo)) (not (== egoGait MOVE_SNEAK)))
			(curRoom setScript: (ScriptID 850 19))
		)
		(super doit: &rest)
	)
)
