;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use PolyPath)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom7 0
)

(local
	saveBits
)
(instance demoRoom7 of Room
	(properties
		picture 350
		style IRISIN
	)
	
	(method (init)
		(Load PICTURE 795)
		(Load VIEW 108)
		(Load SOUND 1006)
		(super init:)
		(oriley init:)
		(yvette init:)
		(ziggy init:)
		(watney init:)
		(countess setStep: 2 2 setCycle: Walk init:)
		(tut
			cycleSpeed: 10
			moveSpeed: 10
			setStep: 4
			init:
			hide:
			stopUpd:
		)
		(himmler init: hide: setStep: 2 2 stopUpd:)
		(music number: 1005 loop: 1 play:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(tut setScript: sEveryOneLeaves)
				(= cycles 1)
			)
			(1
				(DoDisplay
					{For the wealthy partygoers...}
					0
					20071
					190
					20
					20
					myTextColor
					myBordColor
				)
				(= seconds 3)
			)
			(2
				(DoDisplay
					{for the museum staff...}
					0
					20071
					190
					70
					52
					myTextColor
					myBordColor
				)
				(= seconds 3)
			)
			(3
				(DoDisplay
					{and for Laura Bow...}
					0
					20071
					150
					120
					84
					myTextColor
					myBordColor
				)
				(= seconds 3)
			)
			(4
				(DoDisplay
					{THIS PARTY IS OVER.}
					0
					20071
					150
					170
					116
					myTextColor
					myBordColor
				)
				(music fade:)
				(= seconds 3)
			)
			(5
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(cast eachElementDo: #dispose)
				(sparkle1 init:)
				(sparkle2 init:)
				(sparkle3 init:)
				(curRoom drawPic: 795 IRISIN)
				(= cycles 3)
			)
			(6
				(endingSong play:)
				(= cycles 1)
			)
			(7 (= seconds 5))
			(8
				(Display 7 0 p_restore saveBits)
				(= saveBits
					(Display 7 1
						p_mode teJustLeft
						p_color global116
						p_font 20071
						p_at 10 177
						p_save
					)
				)
				(sparkle1 show: setCycle: EndLoop)
				(= seconds 6)
			)
			(9
				(sparkle2 show: setCycle: BegLoop self)
			)
			(10
				(sparkle3 show: setCycle: EndLoop self)
			)
			(11 (= seconds 2))
			(12
				(Display 7 0 p_restore saveBits)
				(UnLoad PICTURE 350 795)
				(curRoom newRoom: 8)
			)
		)
	)
)

(instance sEveryOneLeaves of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(yvette setCycle: Walk setMotion: PolyPath 330 187 yvette)
				(ziggy setCycle: Walk setMotion: PolyPath 330 187 ziggy)
				(watney setCycle: Walk setMotion: PolyPath 330 187 watney)
				(countess setMotion: PolyPath 330 187 countess)
			)
			(1 (self dispose:))
		)
	)
)

(instance sHeWrites of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oriley cycleSpeed: 8 setCycle: EndLoop self)
			)
			(1 (oriley setCycle: BegLoop self))
			(2 (self dispose:))
		)
	)
)

(instance oriley of Actor
	(properties
		x 272
		y 178
		view 353
		loop 1
		priority 14
		signal fixPriOn
	)
)

(instance yvette of Actor
	(properties
		x 257
		y 186
		view 817
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (self onControl:) cLRED) (self setScript: sHeWrites))
		)
		(super doit:)
	)
	
	(method (cue)
		(tut show:)
		(tut setCycle: Walk setMotion: PolyPath 330 187)
		(self dispose:)
	)
)

(instance ziggy of Actor
	(properties
		x 159
		y 187
		view 816
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (self onControl:) cLRED) (self setScript: sHeWrites))
		)
		(super doit:)
	)
	
	(method (cue)
		(himmler show:)
		(himmler setCycle: Walk setMotion: PolyPath 330 187)
		(self dispose:)
	)
)

(instance watney of Actor
	(properties
		x 78
		y 187
		view 815
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (self onControl:) cLRED) (self setScript: sHeWrites))
		)
		(super doit:)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance countess of Actor
	(properties
		x 34
		y 187
		view 813
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (self onControl:) cLRED) (self setScript: sHeWrites))
		)
		(super doit:)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance tut of Actor
	(properties
		x -6
		y 187
		view 821
	)
)

(instance himmler of Actor
	(properties
		x -10
		y 187
		view 814
	)
)

(instance sparkle1 of Prop
	(properties
		x 99
		y 20
		view 108
		loop 1
	)
)

(instance sparkle2 of Prop
	(properties
		x 62
		y 77
		view 108
		loop 1
		cel 12
	)
)

(instance sparkle3 of Prop
	(properties
		x 218
		y 174
		view 108
	)
)

(instance endingSong of Sound
	(properties
		number 1006
	)
)
