;;; Sierra Script 1.0 - (do not remove this comment)
(script# OPENING) ;100
(include game.sh) (include "100.shm")
(use Main)
(use GloryTalker)
(use Talker)
(use MoveFwd)
(use LoadMany)
(use Reverse)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	openCart 0
	avisTalker 1
)

(local
	[aCandle 6]
)
(instance openCart of Room
	(properties
		picture 100
		style PIXELDISSOLVE
		vanishingY -200
	)
	
	(method (init)
		(Narrator color: 10)
		(HandsOff)
		(self setRegions: INTRO)
		(theIconBar disable:)
		(= [aCandle 0] (candle new:))
		(= [aCandle 1] (candle new:))
		(= [aCandle 2] (candle new:))
		(= [aCandle 3] (candle new:))
		(= [aCandle 4] (candle new:))
		(LoadMany RES_SOUND 100 913 902)
		(switch heroType
			(FIGHTER
				(Load RES_SOUND 101)
				(Load RES_VIEW 650)
			)
			(THIEF
				(Load RES_SOUND 12)
				(Load RES_SOUND 900)
			)
			(MAGIC_USER
				(Load RES_SOUND 12)
				(Load RES_SOUND 900)
				(Load RES_SOUND 13)
			)
			(PALADIN
				(Load RES_SOUND 101)
			)
		)
		(candle
			x: 126
			y: 97
			cel: (Random 0 (candle lastCel:))
			setCycle: Forward
			init:
		)
		([aCandle 0]
			x: 102
			y: 80
			cel: (Random 0 (candle lastCel:))
			setCycle: Forward
			init:
		)
		([aCandle 1]
			x: 132
			y: 64
			cel: (Random 0 (candle lastCel:))
			setCycle: Forward
			init:
		)
		([aCandle 2]
			x: 182
			y: 67
			cel: (Random 0 (candle lastCel:))
			setCycle: Forward
			init:
		)
		([aCandle 3]
			x: 211
			y: 83
			cel: (Random 0 (candle lastCel:))
			setCycle: Forward
			init:
		)
		([aCandle 4]
			x: 186
			y: 102
			cel: (Random 0 (candle lastCel:))
			setCycle: Forward
			init:
		)
		(avis setScale: 190 init:)
		(cond 
			((== heroType THIEF)
				(ego
					x: 27
					y: 119
					setScale: 190
					init:
					changeGait: 2
					setCycle: Walk
				)
				(pillar init: setPri: 11 stopUpd:)
				(pillar2 init: setPri: 7 stopUpd:)
			)
			((or (== heroType MAGIC_USER) (Btst fWasWizard))
				(ego
					x: 325
					y: 86
					setScale: 190
					normalize:
					init:
					setHeading: 300
					setStep: 3 2
				)
			)
			((or (== heroType FIGHTER) (== heroType PALADIN))
				(ego x: 325 y: 86 setScale: 190 normalize: init:)
				(avisTalker
					x: 4
					y: 94
					textX: 140
					textY: 30
					talkWidth: 145
				)
			)
		)
		(if (or (!= heroType FIGHTER) (Btst fWasWizard))
			(spellImage setLoop: 0 setScale: 190 init: hide:)
		)
		(brazier
			setScale: 190
			setLoop: (if (== heroType MAGIC_USER) 1 else 0)
			x: (if (== heroType MAGIC_USER) 87 else 60)
			y: (if (== heroType MAGIC_USER) 68 else 87)
			init:
		)
		(super init: &rest)
		(curRoom setScript: charSwitch)
	)
	
	(method (dispose)
		(DisposeScript MOVEFWD)
		(theIconBar enable:)
		(Narrator color: 17)
		(UnLoad RES_VIEW 650)
		(UnLoad RES_SOUND 13)
		(UnLoad RES_SOUND 900)
		(UnLoad RES_SOUND 12)
		(UnLoad RES_SOUND 902)
		(UnLoad RES_SOUND 913)
		(UnLoad RES_SOUND 101)
		(super dispose:)
	)
)

(instance charSwitch of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(cSound number: 100 play: 127 self hold: 1)
			)
			(2
				(cSound client: 0)
				(Narrator keepWindow: 0)
				(messager say: N_ROOM V_DOIT C_START 0 self)
			)
			(3
				(avis setLoop: 1 setCycle: EndLoop self)
			)
			(4
				(narrator y: 120)
				(messager say: N_AVIS V_DOIT C_CHANT 0 self)
			)
			(5
				(cSound hold: 2)
				(self
					setScript:
						(cond 
							((and (== heroType PALADIN) (Btst fWasWizard)) mageCartoon)
							((== heroType FIGHTER) fighterCartoon)
							((== heroType MAGIC_USER) mageCartoon)
							((== heroType THIEF) thiefCartoon)
							(else fighterCartoon)
						)
						self
				)
			)
			(6
				(cSound release:)
				(= cycles 3)
			)
			(7
				(curRoom newRoom: 110)
			)
		)
	)
)

(instance knockBrazier of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(avis view: 103 loop: 0 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(1
				(brazier setCycle: EndLoop)
				(avis setCycle: EndLoop self)
			)
			(2
				(avis loop: 1 cel: 0 setCycle: EndLoop)
				(globalSound number: 101 play: self)
				(flame init: setCycle: EndLoop self)
			)
			(3
				(brazier setLoop: 2 setCycle: Forward)
			)
			(4
				(globalSound number: 913 setLoop: -1 play:)
				(flame2 setPri: 1 setCycle: EndLoop self init:)
				(flame setLoop: 1 setCycle: Forward)
			)
			(5
				(flame2 setLoop: 3 cel: 0 setCycle: Forward)
				(avis view: 101 loop: 0 cel: 0)
				(self dispose:)
			)
		)
	)
)

(instance fighterCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego changeGait: 1 setMotion: MoveTo 217 83 self)
			)
			(1
				(globalSound number: 902 play:)
				([aCandle 3]
					cel: 0
					loop: 1
					x: (- ([aCandle 3] x?) 8)
				)
				(= cycles 6)
			)
			(2
				([aCandle 3] setCycle: EndLoop self)
			)
			(3 (messager say: N_ROOM V_DOIT C_KNOCKED_CANDLE 0 self))
			(4
				(cSound hold: 3)
				(avis setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(5 (messager say: N_AVIS V_DOIT C_DESTROYED_SPELL 0 self))
			(6
				(ego setMotion: MoveTo 186 56 self)
				(self setScript: knockBrazier self)
			)
			(7 (cSound hold: 4))
			(8
				(ego normalize: 5)
				(messager say: N_AVIS V_DOIT C_FIRE_RING 0 self)
			)
			(9
				(ego
					view: 109
					setCycle: Walk
					setLoop: 0
					cel: 0
					setMotion: MoveTo 90 74 self
				)
			)
			(10
				(globalSound number: 901 setLoop: 1 play:)
				(avis
					view: 107
					loop: 0
					cel: 0
					x: 77
					y: 76
					setStep: 6 4
					xStep: 6
					yStep: 4
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 32 85 self
				)
			)
			(11
				(avis view: 105 loop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 912 setLoop: 1 play: 127)
			)
			(12
				(avisBust cel: 1)
				(avisEyes loop: 4)
				(messager say: N_AVIS V_DOIT C_AVIS_DYING 0 self)
			)
			(13 (= seconds 2))
			(14
				(globalSound fade: 0 2 5 1)
				(avis dispose:)
				(messager say: N_ROOM V_DOIT C_PUSHED_HIM 0 self)
			)
			(15
				(cSound release:)
				(self dispose:)
			)
		)
	)
)

(instance mageCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(ego setMotion: MoveTo 290 86 self)
			)
			(2
				(ego view: 14 loop: 1 cel: 0 setCycle: CycleTo 7 1 self)
			)
			(3
				(globalSound number: 12 play:)
				(ego setCycle: BegLoop)
				(spellImage
					ignoreActors: 1
					setLoop: 7
					cel: 0
					setScale: 190
					x: 288
					y: 65
					setCycle: Forward
					show:
					setMotion: JumpTo 182 63 self
				)
			)
			(4
				([aCandle 2] dispose:)
				(spellImage setMotion: JumpTo 280 65 self)
			)
			(5
				(ego normalize:)
				(spellImage hide:)
				(messager say: N_ROOM V_DOIT C_KNOCKED_CANDLE 0 self)
			)
			(6
				(messager say: N_AVIS V_DOIT C_DESTROYED_SPELL 0 self)
			)
			(7
				(cSound hold: 3)
				(avis setLoop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 900 play:)
				(spellImage
					setLoop: 1
					origStep: 5138
					x: 76
					y: 40
					setScale: 190
					show:
					moveSpeed: 0
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 20) self
				)
			)
			(8)
			(9
				(globalSound number: 10 play:)
				(spellImage
					origStep: 5138
					setMotion: MoveTo (- (ego x?) 50) (- (ego y?) 20) self
				)
			)
			(10
				(spellImage setCycle: 0 hide:)
				(messager say: N_ROOM V_DOIT C_EGO_REVERSAL 0 self)
			)
			(11
				(ego view: 14 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(12
				(ego setCycle: BegLoop)
				(globalSound number: 13 play:)
				(spellImage
					x: 285
					y: 67
					origStep: 5138
					xStep: 16
					yStep: 12
					show:
					setCycle: EndLoop
					setMotion: MoveTo 76 52 self
				)
			)
			(13
				(ego normalize:)
				(globalSound number: 11 play:)
				(spellImage origStep: 5138 setMotion: MoveTo 126 53 self)
			)
			(14
				(spellImage setCycle: 0 hide:)
				(messager say: N_ROOM V_DOIT C_AVIS_REVERSAL 0 self)
			)
			(15 (= ticks 60))
			(16
				(ego view: 14 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(17
				(globalSound number: 13 play:)
				(spellImage
					setLoop: 5
					cel: 0
					x: 285
					y: 60
					origStep: 5138
					show:
					setCycle: Forward
					setMotion: MoveTo 147 17 self
				)
				(ego setCycle: BegLoop)
			)
			(18
				(globalSound number: 11 play:)
				(spellImage origStep: 5138 setMotion: MoveTo 76 52 self)
			)
			(19
				(ego normalize:)
				(spellImage dispose:)
				(globalSound number: 902 play:)
				(brazier setCycle: CycleTo 5 1 self)
			)
			(20
				(brazier setCycle: EndLoop self)
				(avis
					view: 107
					loop: 0
					cel: 0
					x: 77
					y: 76
					setStep: 4 3
					xStep: 4
					yStep: 3
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 32 85 self
				)
			)
			(21 0)
			(22
				(brazier setLoop: 2 setCycle: Forward)
				(= cycles 6)
			)
			(23
				(avis view: 105 loop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 912 setLoop: 1 play: 127)
			)
			(24
				(avisBust cel: 1)
				(avisEyes loop: 4)
				(messager say: N_AVIS V_DOIT C_AVIS_DYING 0 self)
			)
			(25
				(cSound hold: 4)
				(= seconds 2)
			)
			(26
				(messager say: N_ROOM V_DOIT C_FORCE_BOLTED_HIM 0 self)
			)
			(27
				(cSound release:)
				(self dispose:)
			)
		)
	)
)

(instance thiefCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(ego
					view: 9
					loop: 2
					cel: 0
					x: 27
					y: 119
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(ego setHeading: 90)
				(dag
					x: 66
					y: 84
					init:
					setStep: 5 3
					setScale: 190
					setLoop: 2
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 102 80 self
				)
				(globalSound number: 916 setLoop: 1 play: 127)
				(ego setCycle: EndLoop)
			)
			(3
				(ego view: 2)
				(dag dispose:)
				(globalSound number: 902 play:)
				([aCandle 0] loop: 2 setCycle: EndLoop self)
			)
			(4 (messager say: N_ROOM V_DOIT C_KNOCKED_CANDLE 0 self))
			(5 (messager say: N_AVIS V_DOIT C_DESTROYED_SPELL 0 self))
			(6
				(cSound hold: 3)
				(avis setLoop: 0 cel: 0 setCycle: CycleTo 5 1 self)
			)
			(7
				(globalSound number: 900 play:)
				(spellImage
					x: 95
					y: 35
					init:
					setPri: 9
					origStep: 5138
					setScale: 190
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					show:
					setMotion: MoveTo 45 70 self
				)
				(avis setCycle: EndLoop)
			)
			(8
				(spellImage origStep: 5138 setMotion: MoveFwd 120)
				(ego view: 108 setLoop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(9 (= ticks 60))
			(10 (ego setCycle: EndLoop self))
			(11
				(ego
					normalize:
					changeGait: 1
					setMotion: MoveTo 169 164 self
				)
			)
			(12
				(ego setMotion: MoveTo 183 164 self)
			)
			(13
				(spellImage hide:)
				(ego changeGait: 2)
				(= ticks 30)
			)
			(14
				(avis cel: 0 setCycle: CycleTo 5 1 self)
			)
			(15
				(globalSound number: 900 play:)
				(spellImage
					x: 95
					y: 35
					show:
					setPri: 7
					origStep: 5138
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 175 112 self
				)
			)
			(16
				(globalSound number: 13 play:)
				(spellImage
					origStep: 5138
					yStep: 12
					setMotion: MoveTo 200 105 self
				)
			)
			(17
				(spellImage setCycle: 0 hide:)
				(ego changeGait: 1 setMotion: MoveTo 235 144 self)
			)
			(18
				(ego setHeading: 315)
				(avis cel: 0 setCycle: CycleTo 5 1 self)
			)
			(19
				(globalSound number: 900 play:)
				(spellImage
					x: 95
					y: 35
					show:
					setPri: 7
					origStep: 5138
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 189 95 self
				)
			)
			(20
				(spellImage
					setPri: 11
					origStep: 5138
					setMotion: MoveFwd 180
				)
				(ego view: 108 setLoop: 3 setCycle: CycleTo 4 1 self)
			)
			(21 (= ticks 45))
			(22 (ego setCycle: EndLoop self))
			(23
				(spellImage dispose:)
				(ego view: 9 setLoop: 3 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(24
				(dag
					x: 195
					y: 96
					origStep: 2568
					cycleSpeed: 0
					setScale: 190
					moveSpeed: 0
					setCycle: Reverse
					setMotion: MoveTo 105 60 self
					init:
				)
				(globalSound number: 916 setLoop: 1 play: 127)
				(ego setCycle: EndLoop)
			)
			(25
				(dag dispose:)
				(avis
					view: 107
					cel: 0
					moveSpeed: 0
					ignoreActors: 1
					setCycle: Reverse
					setMotion: MoveTo 32 85 self
				)
			)
			(26
				(avis view: 105 loop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 912 setLoop: 1 play: 127)
			)
			(27
				(avisBust cel: 1)
				(avisEyes loop: 4)
				(messager say: N_AVIS V_DOIT C_AVIS_DYING 0 self)
			)
			(28
				(cSound hold: 4)
				(= seconds 2)
			)
			(29
				(avis dispose:)
				(= cycles 3)
			)
			(30
				(narrator y: 30)
				(messager say: N_ROOM V_DOIT C_DAGGERED_HIM 0 self)
			)
			(31
				(cSound release:)
				(narrator y: 120)
				(self dispose:)
			)
		)
	)
)

(instance flame of Prop
	(properties
		x 65
		y 89
		view 650
		signal ignrAct
	)
)

(instance flame2 of Prop
	(properties
		x 65
		y 89
		view 650
		loop 2
		signal ignrAct
	)
)

(instance dag of Actor
	(properties
		view 46
		loop 2
		signal ignrAct
	)
)

(instance avis of Actor
	(properties
		x 76
		y 76
		view 101
	)
)

(instance brazier of Prop
	(properties
		x 60
		y 87
		view 100
		signal ignrAct
	)
)

(instance pillar of Prop
	(properties
		x 155
		view 106
		cel 1
		signal (| ignrAct ignrHrz)
	)
)

(instance pillar2 of Prop
	(properties
		x 4
		view 106
		signal (| ignrAct ignrHrz)
	)
)

(instance avisTalker of GloryTalker
	(properties
		x 110
		y 15
		view 119
		loop 1
		talkWidth 210
		back 57
		textX -60
		textY 100
	)
	
	(method (init)
		(super init: avisBust avisEyes avisMouth &rest)
	)
	
	(method (show)
		(super show:)
		(DrawCel
			(bust view?)
			(bust loop?)
			(bust cel?)
			(+ (bust nsLeft?) nsLeft)
			(+ (bust nsTop?) nsTop)
			-1
		)
	)
)

(instance avisBust of Prop
	(properties
		nsTop 28
		nsLeft 33
		view 119
		loop 3
	)
)

(instance avisMouth of Prop
	(properties
		nsTop 56
		nsLeft 33
		view 119
		cycleSpeed 10
	)
)

(instance avisEyes of Prop
	(properties
		nsTop 39
		nsLeft 34
		view 119
		loop 2
		cycleSpeed 30
	)
)

(instance candle of Prop
	(properties
		view 102
		signal ignrAct
	)
)

(instance spellImage of Actor
	(properties
		view 21
		signal ignrAct
	)
)
