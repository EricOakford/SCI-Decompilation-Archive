;;; Sierra Script 1.0 - (do not remove this comment)
(script# 545)
(include game.sh)
(use Main)
(use brain)
(use RegionPath)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Osc)
(use RandCyc)
(use LoadMany)
(use Motion)
(use System)

(public
	rm545 0
)

(local
	saveBits
	local1
	[brainOverlay 4] = [0 548 549 551]
	brainLevel
	[blipPosn 270] =
		[0 0 69 125 85 125 85 120 100 120 0 0 105 120 123 120 134 130 160 130 169 114 187 114 0 0 192 114 200 114 200 95
		0 0 200 90 200 72 178 72 0 0 173 72 135 72 156 72 156 83 0 0 156 86 161 130 134 130 123 119 105 119 0 0 100 119 85
		119 78 114 78 114 77 110 85 110 85 85 0 0 85 80 85 73 111 72 0 0 116 72 131 72 133 59 131 72 116 72 0 0 111 72 85 73
		85 80 0 0 85 85 85 101 109 101 0 0 112 101 123 101 130 93 159 93 168 102 187 102 0 0 192 102 197 102 197 93 0 0 197 88
		197 72 182 72 0 0 177 72 156 72 154 59 156 72 177 72 0 0 182 72 197 72 197 102 0 0 197 93 197 102 240 102 240 125 240
		126 203 126 203 116 192 116 0 0 187 116 168 116 161 126 133 126 123 114 105 114 0 0 100 114 84 114 84 110 84 110 77
		110 78 114 85 119 100 119 0 0 105 119 123 119 134 86 0 0 134 83 134 72 173 72 0 0 178 72 200 72 200 90 0 0 200 95
		200 114 192 114 0 0 187 114 169 114 160 130 134 130 123 120 105 120 0 0 100 120 85 120 85 125 69 125]
	[local277 24] = [3500 100 73 2505 140 71 3510 182 72 3525 187 115 2525 189 114 3515 93 114 2515 73 125 1515 92 119]
)
(procedure (VohaulSays param1 theString theSound soundIsPlaying)
	(DoDisplay saveBits)
	(= saveBits
		(DoDisplay theString
			#at 60 (if (== param1 0) 130 else 10)
			#color (if (== param1 0) myTextColor5 else myTextColor22)
			#back myLowlightColor
			#width 255
		)
	)
	(if theSound
		(globalSound number: theSound vol: 127 loop: 1 play: soundIsPlaying)
	)
)

(instance rm545 of SQRoom
	(properties
		picture 545
	)
	
	(method (init &tmp temp0 temp1 theX theY)
		(LoadMany PICTURE 546 547 548 549 551)
		(LoadMany VIEW 506 546)
		(theIconBar disable: (theIconBar at: ICON_WALK))
		(theGame setCursor: 851)
		(theIconBar curIcon: (theIconBar at: ICON_LOOK))
		(self setRegions: BRAIN)
		(super init:)
		(SolvePuzzle fUseLaptopInBrain 10)
		(if (== (++ laptopUses) 2)
			(self setScript: vohaulScript)
		else
			(= brainLevel (brain level?))
			(curRoom overlay: [brainOverlay brainLevel])
			(if (not (Btst fDeletedDroids))
				(blip1
					view: 506
					setLoop: 3
					setStep: 2 2
					x: [blipPosn (- (((ScriptID BRAIN 1) mover?) value?) 1)]
					y: [blipPosn (((ScriptID BRAIN 1) mover?) value?)]
					init:
					setCycle: Oscillate
				)
				(blip2
					view: 506
					setLoop: 4
					setStep: 2 2
					x: [blipPosn (- (((ScriptID BRAIN 2) mover?) value?) 1)]
					y: [blipPosn (((ScriptID BRAIN 2) mover?) value?)]
					init:
					setCycle: Oscillate
				)
			)
			(= temp1
				(+ (* (brain level?) 1000) (brain lastRoom?))
			)
			(= temp0 0)
			(while (!= temp1 [local277 temp0])
				(= temp0 (+ temp0 3))
			)
			(= theX [local277 (++ temp0)])
			(= theY [local277 (++ temp0)])
			(blip3
				view: 506
				setLoop: 5
				x: theX
				y: theY
				init:
				setCycle: Oscillate
			)
			(level1 init:)
			(level2 init:)
			(level3 init:)
			(powerButton init:)
			(keyboard init:)
			(dandyName init:)
		)
		(ego init: hide:)
		(music fade: 20 1 10 0)
		(globalSound number: 843 vol: 127 loop: 1 play:)
	)
	
	(method (doit &tmp [temp0 40] temp40 temp41 temp42 temp43 temp44 temp45)
		(super doit:)
		(if (and local1 (not (-- local1))) (EgoDead iconLASER deathDROIDTAGGED))
		(= temp44 ((ScriptID BRAIN 1) mover?))
		(= temp45 ((ScriptID BRAIN 2) mover?))
		(cond 
			(script)
			((Btst fDeletedDroids))
			((not temp44) (if (not local1) (= local1 200)))
			((temp44 isKindOf: RegionPath)
				(= temp40 (- (temp44 value?) 1))
				(= temp41 (temp44 value?))
				(blip1 setMotion: MoveTo [blipPosn temp40] [blipPosn temp41])
			)
		)
		(cond 
			(script)
			((Btst fDeletedDroids))
			((not temp45) (if (not local1) (= local1 200)))
			((temp45 isKindOf: RegionPath)
				(= temp42 (- (((ScriptID BRAIN 2) mover?) value?) 1))
				(= temp43 (((ScriptID BRAIN 2) mover?) value?))
				(blip2 setMotion: MoveTo [blipPosn temp42] [blipPosn temp43])
			)
		)
	)
	
	(method (dispose)
		(music fade: 127 1 10 0)
		(globalSound number: 844 vol: 127 loop: 1 play:)
		(HandsOn)
		(brain lastRoom: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 545 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance powerButton of Sq4Feature
	(properties
		x 37
		y 116
		nsTop 92
		nsLeft 28
		nsBottom 140
		nsRight 47
		lookStr 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(curRoom newRoom: (brain lastRoom?))
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance keyboard of Sq4Feature
	(properties
		x 151
		y 179
		nsTop 170
		nsBottom 189
		nsRight 303
		sightAngle 90
		lookStr 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(narrator modNum: 545 say: 4)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance dandyName of Sq4Feature
	(properties
		x 78
		y 163
		nsTop 158
		nsLeft 20
		nsBottom 167
		nsRight 95
		lookStr 5
	)
)

(instance level1 of Sq4Feature
	(properties
		x 222
		y 35
		nsTop 31
		nsLeft 244
		nsBottom 40
		nsRight 257
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK)
			(if (!= brainLevel 1)
				(curRoom overlay: [brainOverlay 1])
				(if (== (= brainLevel 1) (brain level?))
					(blip3 show:)
				else
					(blip3 hide:)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance level2 of Sq4Feature
	(properties
		x 222
		y 35
		nsTop 31
		nsLeft 230
		nsBottom 40
		nsRight 243
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK)
			(if (!= brainLevel 2)
				(curRoom overlay: [brainOverlay 2])
				(if (== (= brainLevel 2) (brain level?))
					(blip3 show:)
				else
					(blip3 hide:)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance level3 of Sq4Feature
	(properties
		x 222
		y 35
		nsTop 31
		nsLeft 216
		nsBottom 40
		nsRight 229
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK)
			(if (!= brainLevel 3)
				(curRoom overlay: [brainOverlay 3])
				(if (== (= brainLevel 3) (brain level?))
					(blip3 show:)
				else
					(blip3 hide:)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance blip1 of Sq4Actor
	(properties
		priority 10
		signal $6010
	)
	
	(method (doit)
		(super doit:)
		(if (== ((ScriptID BRAIN 1) level?) brainLevel)
			(self show:)
		else
			(self hide:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(narrator modNum: 545 say: 7)
			)
			(1
				(if ((ScriptID BRAIN 1) mover?)
					(narrator modNum: 545 say: 7)
				else
					(narrator modNum: 545 say: 8)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blip2 of Sq4Actor
	(properties
		priority 10
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doit)
		(super doit:)
		(if (== ((ScriptID BRAIN 2) level?) brainLevel)
			(self show:)
		else
			(self hide:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(narrator modNum: 545 say: 7)
			)
			(V_LOOK
				(if ((ScriptID BRAIN 2) mover?)
					(narrator modNum: 545 say: 7)
				else
					(narrator modNum: 545 say: 8)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blip3 of Sq4Prop
	(properties
		priority 10
		signal fixPriOn
		lookStr 9
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_TALK)
			(narrator modNum: 545 say: 10)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance vohaulScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom overlay: 546)
				(vohaulMouth init:)
				(vohaulEyes init:)
				(= seconds 3)
			)
			(1
				(if (== msgType CD_MSG)
					(tVOHAUL
						init: 0 vohaulEyes vohaulMouth
						modNum: 545
						say: 1 self
					)
				else
					(vohaulMouth setCycle: Forward)
					(vohaulEyes setCycle: RandCycle)
					(VohaulSays 0
						{_______Take a good look, Roger:}
						851 self
					)
				)
			)
			(2
				(if (== msgType TEXT_MSG) (DoDisplay saveBits))
				(vohaulEyes dispose:)
				(vohaulMouth dispose:)
				(curRoom overlay: 547)
				(= seconds 3)
			)
			(3
				(if (== msgType CD_MSG)
					(tVOHAUL say: 2 self)
				else
					(VohaulSays 0
						{Remember this poor wretched soul...}
						852 self
					)
				)
			)
			(4
				(if (== msgType TEXT_MSG)
					(DoDisplay saveBits)
					(VohaulSays 0
						{_________...for he is your SON!}
						853 self
					)
				else
					(= cycles 1)
				)
			)
			(5
				(HandsOn)
				(curRoom newRoom: (brain lastRoom?))
			)
		)
	)
)

(instance vohaulEyes of Sq4Prop
	(properties
		x 153
		y 65
		view 546
		priority 12
		signal fixPriOn
	)
)

(instance vohaulMouth of Sq4Prop
	(properties
		x 155
		y 90
		view 546
		loop 1
		priority 11
		signal fixPriOn
	)
)

(instance tVOHAUL of FaceTalker
	(properties
		noun VOHAULJR
		talkerNum VOHAULJR
	)
)
