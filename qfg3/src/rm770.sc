;;; Sierra Script 1.0 - (do not remove this comment)
(script# 770)
(include sci.sh)
(use Main)
(use TellerIcon)
(use EgoDead)
(use PolyPath)
(use Polygon)
(use Block)
(use Feature)
(use Track)
(use LoadMany)
(use Wander)
(use Chase)
(use Orbit)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm770 0
)

(local
	local0
	local1
	local2
	[newGem1 5]
	[local8 8] = [0 5 6 7 -9 -8 -10 999]
	[local16 2]
	[local18 11] = [0 -21 -18 -32 -22 -27 -28 -26 -19 -20 999]
	[local29 2]
)
(procedure (localproc_0d9d)
	(soundFx number: 772 setLoop: -1 play: 127)
	(Bset 137)
	(cond 
		((== global394 0)
			(++ global394)
			(guardian moveSpeed: 2 cycleSpeed: 2)
			(ego addHonor: -20)
		)
		((== global394 1)
			(++ global394)
			(guardian moveSpeed: 0 cycleSpeed: 0)
			(ego addHonor: -30)
		)
		((== global394 2) (ego setScript: willSpin))
		((== global394 3) (guardian setScript: kickHimOut))
	)
)

(instance rm770 of Rm
	(properties
		noun 10
		picture 770
		vanishingY -25
	)
	
	(method (init)
		(Bset 132)
		(= [local16 0] @local8)
		(= [local29 0] @local18)
		(LoadMany 128 6 771)
		(super init:)
		(globalSound stop:)
		(globalSound number: 914 setLoop: -1 play: 127)
		(soundFx number: 771 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 110 189 319 189 319 106 241 98 220 115 134 119 39 119
					yourself:
				)
		)
		(mushrooms init:)
		(globes init:)
		(vines init:)
		(upperGlobes init:)
		(plant init:)
		(bigVine init:)
		(if (Btst 51)
			(gem1 init:)
			((= [newGem1 1] (gem1 new:))
				loop: 0
				cel: 1
				x: 174
				y: 137
				init:
				addToPic:
			)
			((= [newGem1 2] (gem1 new:))
				loop: 0
				cel: 0
				x: 168
				y: 144
				init:
				addToPic:
			)
			((= [newGem1 3] (gem1 new:))
				loop: 0
				cel: 0
				x: 149
				y: 140
				init:
				addToPic:
			)
			((= [newGem1 4] (gem1 new:))
				loop: 0
				cel: 1
				x: 159
				y: 143
				init:
				addToPic:
			)
		)
		(ego x: 304 y: 134 setScale: 189 init: noun: 4 normalize:)
		(guardian
			maxScale: 200
			moveSpeed: 4
			ignoreHorizon: 1
			observeBlocks: deCage
			init:
			setScript: guardianOrbits
		)
		(egoTell init: ego @local18 @local29)
		(cond 
			((== global394 1) (guardian moveSpeed: 2 cycleSpeed: 2))
			((== global394 2) (guardian moveSpeed: 0 cycleSpeed: 0))
		)
		(ego setScript: egoEnters)
	)
	
	(method (dispose)
		(soundFx stop:)
		(UnLoad 128 6)
		(UnLoad 128 771)
		(LoadMany 0 986 964 955 970 949)
		(DisposeClone gem1)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81
				(self setScript: (ScriptID 32 0) 0 theVerb)
				(localproc_0d9d)
			)
			(83
				(self setScript: (ScriptID 32 0) 0 theVerb)
				(localproc_0d9d)
			)
			(32
				(self setScript: (ScriptID 32 0) 0 theVerb)
				(localproc_0d9d)
			)
			(20
				(self setScript: (ScriptID 32 0) 0 theVerb)
				(localproc_0d9d)
			)
			(33
				(self setScript: (ScriptID 32 0) 0 theVerb)
				(localproc_0d9d)
			)
			(74
				(if (cast contains: guardian)
					(messager say: 3 6 33)
				else
					(self setScript: goToBed)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance goToBed of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 179 148 self)
			)
			(1
				(ego view: 35 loop: 0 cel: 0 x: 198 setCycle: End self)
			)
			(2
				(if (= temp0 (PalVary pvGET_CURRENT_STEP))
					(if (< temp0 64)
						(PalVary pvCHANGE_TICKS 3)
						(= seconds 5)
					else
						(self cue:)
					)
				else
					(PalVary pvINIT 310 3)
					(Btst 81)
					(= seconds 15)
				)
			)
			(3
				(PalVary pvREVERSE 3)
				(Bclr 81)
				(= seconds 4)
			)
			(4
				((ScriptID 7 7) init: 5 40)
				(ego setCycle: Beg self)
			)
			(5
				(ego
					normalize: 6
					cel: 6
					x: 179
					changeGait: 0
					code: outCheck
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance gemsAppear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guardTell dispose:)
				(guardian show: setMotion: MoveTo 171 100 self)
				(= local0 0)
				(ego normalize: noun: 4)
			)
			(1 (= seconds 3))
			(2
				(gem1 init:)
				((= [newGem1 1] (gem1 new:))
					loop: 0
					cel: 1
					x: 174
					y: 137
					init:
				)
				((= [newGem1 2] (gem1 new:))
					loop: 0
					cel: 0
					x: 168
					y: 144
					init:
				)
				((= [newGem1 3] (gem1 new:))
					loop: 0
					cel: 0
					x: 149
					y: 140
					init:
				)
				((= [newGem1 4] (gem1 new:))
					loop: 0
					cel: 1
					x: 159
					y: 143
					init:
				)
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(= cycles 2)
			)
			(3
				(guardian setMotion: Wander 75 observeBlocks: deCage)
				(gem1 stopUpd:)
				([newGem1 1] addToPic:)
				([newGem1 2] addToPic:)
				([newGem1 3] addToPic:)
				([newGem1 4] addToPic:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance willSpin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(if local1
					(ego setScript: kickHimOut)
				else
					(ego setScript: egoSpins)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoGetsGem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 181 146 self)
				(if (not (Btst 51))
					(ego get: 36 addHonor: 20 solvePuzzle: 326 5)
					(Bset 51)
				else
					(= global394 3)
					(messager say: 1 6 31)
					(guardian setScript: kickHimOut)
				)
			)
			(1
				(if (not (== global394 3))
					(ego view: 4 loop: 1 cel: 0 setCycle: End self)
				)
			)
			(2
				(gem1 dispose:)
				(ego setCycle: Beg self)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance kickHimOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cast contains: guardian))
					(guardian init: observeBlocks: deCage)
				)
				(HandsOff)
				(ego code: 0 moveSpeed: 0 setMotion: 0)
				(guardian
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo (ego x?) (ego y?) self
				)
			)
			(1
				(guardian
					ignoreBlocks: deCage
					setMotion: MoveTo 304 134 self
				)
				(ego
					view: 6
					setScale:
					setLoop: 2
					setCel: 1
					setCycle: 0
					setMotion: tracker guardian
				)
			)
			(2 (EgoDead))
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(messager say: 3 6 17)
				(ego setMotion: PolyPath 181 146 self)
			)
			(2
				(ego code: outCheck)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoSpins of Script
	(properties)
	
	(method (doit)
		(if (< (guardianOrbits state?) 2)
			(ego
				setHeading: (GetAngle
					(ego x?)
					(ego y?)
					(guardian x?)
					(guardian y?)
				)
			)
		)
		(if
		(and (== (guardianOrbits state?) 2) (== state 0))
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 5 cel: 0)
			)
			(1
				(ego view: 6 loop: 3 setCycle: End)
				(= seconds 3)
			)
			(2
				(ego loop: 9 cel: 0 setCycle: End self)
			)
			(3 (DontMove) (self dispose:))
		)
	)
)

(instance guardianOrbits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guardian setCycle: Fwd setMotion: Wander 75)
				(= seconds 10)
			)
			(1
				(guardian
					z: (+ (ego z?) 20)
					ignoreBlocks: deCage
					setMotion: Orbit ego 25 0 0 45
				)
				(= seconds 15)
			)
			(2
				(DontMove)
				(guardian setMotion: Chase ego 0 self)
			)
			(3
				(egoTell dispose:)
				(guardTell init: ego @local8 @local16)
				(= local0 1)
				(ego view: 772 loop: 1 noun: 1 setCycle: End)
				(guardian hide:)
				(messager say: 3 6 2 0 self)
			)
			(4
				(switch global394
					(0 (messager say: 1 6 1))
					(1 (messager say: 1 6 3))
					(2 (messager say: 1 6 4))
				)
				(DontMove)
				(= local1 1)
				(curRoom setScript: contactScript)
				(self dispose:)
			)
		)
	)
)

(instance contactScript of Script
	(properties)
	
	(method (dispose)
		(ego code: outCheck)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DontMove)
				(egoTell init: ego @local18 @local29)
				(ego view: 772 loop: 0 code: 0 normalize: 2 noun: 4)
				(= seconds 3)
			)
			(1
				(guardTell init: ego @local8 @local16)
				(ego view: 772 loop: 1 setCycle: Fwd noun: 1)
				(= seconds 3)
			)
			(2 (ego setCycle: Beg self))
			(3 (self init:))
		)
	)
)

(instance goodBye of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guardTell dispose:)
				(egoTell dispose:)
				(ego normalize: noun: 4 actions: 0)
				(guardian show: setMotion: MoveTo 20 40 self)
			)
			(1
				(guardian dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance guardian of Actor
	(properties
		x 100
		y 100
		noun 1
		view 771
		signal $4000
	)
)

(instance gem1 of View
	(properties
		x 160
		y 138
		noun 12
		view 770
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (ego setScript: egoGetsGem))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mushrooms of Feature
	(properties
		x 25
		y 170
		noun 2
		nsTop 151
		nsBottom 189
		nsRight 51
		sightAngle 180
	)
)

(instance globes of Feature
	(properties
		x 295
		y 157
		noun 5
		nsTop 126
		nsLeft 272
		nsBottom 188
		nsRight 319
		sightAngle 180
	)
)

(instance vines of Feature
	(properties
		x 91
		y 100
		noun 6
		nsTop 35
		nsLeft 54
		nsBottom 165
		nsRight 128
		sightAngle 180
	)
)

(instance upperGlobes of Feature
	(properties
		x 304
		y 58
		noun 9
		nsTop 39
		nsLeft 290
		nsBottom 78
		nsRight 319
		sightAngle 180
	)
)

(instance plant of Feature
	(properties
		x 194
		y 171
		noun 7
		nsTop 156
		nsLeft 162
		nsBottom 186
		nsRight 227
		sightAngle 180
	)
)

(instance bigVine of Feature
	(properties
		x 99
		y 107
		noun 8
		nsTop 52
		nsLeft 74
		nsBottom 163
		nsRight 125
		sightAngle 180
	)
)

(instance guardTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-9
				(if (and (== local0 1) (not (Btst 157)))
					(Btst 58)
				else
					0
				)
				-8
				(if
					(and
						(== local0 1)
						(Btst 60)
						(not (cast contains: gem1))
					)
					(not (ego has: 36))
				else
					0
				)
				-10
				(if (and (== local0 1) (Btst 133))
					(not (ego has: 44))
				else
					0
				)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-8
					(curRoom setScript: gemsAppear)
					(ego noun: 1)
					(return query)
				)
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 2) (== local0 0))
			(messager say: 3 6 30)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-18
				(== local0 0)
				-32
				(== local0 1)
				-21
				(== local0 1)
				-22
				(== local0 1)
				-27
				(if (ego has: 39)
					(not ((inventory at: 39) state?))
				else
					0
				)
				-28
				(if (ego has: 39) ((inventory at: 39) state?) else 0)
				-26
				(if (ego has: 37) local0 else 0)
				-19
				(== local0 0)
				-20
				(== local0 1)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-32
					(switch global394
						(0 (return (= query -23)))
						(1 (return (= query -24)))
						(2 (return (= query -25)))
					)
				)
				(-20
					(curRoom setScript: goodBye)
					(return query)
				)
				(else  (return query))
			)
		)
	)
)

(instance tracker of Track
	(properties)
	
	(method (init)
		(if argc (super init: &rest))
	)
	
	(method (doit &tmp temp0)
		(client
			x: (+ (who x?) 10)
			y: (+ (who y?) yOffset)
			z: (+ (who z?) zOffset)
		)
	)
)

(instance deCage of Cage
	(properties
		top 25
		bottom 190
		right 320
	)
)

(instance deBase of Code
	(properties)
	
	(method (doit param1)
		(Animate (cast elements?) 0)
		(BaseSetter param1)
	)
)

(instance outCheck of Code
	(properties)
	
	(method (doit)
		(if (ego inRect: 300 100 319 189)
			(curRoom newRoom: 760)
		)
	)
)
