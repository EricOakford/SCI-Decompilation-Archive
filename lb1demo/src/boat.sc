;;; Sierra Script 1.0 - (do not remove this comment)
(script# 780)
(include game.sh)
(use Main)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	boat 0
)

(local
	saveBits
	saveBits2
)
(instance boat of Room
	(properties
		picture 79
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(Load VIEW 202)
		(Load FONT SYSFONT)
		(Load FONT 41)
		(skiff
			view: 202
			loop: 0
			posn: 11 150
			setPri: 13
			setStep: 1 1
			setMotion: MoveTo 310 150 self
			setScript: Polling
			init:
		)
		(skiff cel: (- (NumCels skiff) 1))
		(ripple
			view: 202
			loop: 1
			cel: 0
			setPri: 12
			setCycle: Forward
			setStep: 1 1
			setScript: Delay
			init:
		)
		(reflection
			view: 202
			loop: 5
			posn: 143 134
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(fly1
			view: 202
			setLoop: 6
			cel: (Random 0 1)
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: picWindow
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(fly2
			view: 202
			setLoop: 6
			cel: (Random 0 1)
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: picWindow
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(fly3
			view: 202
			setLoop: 6
			cel: (Random 0 1)
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: picWindow
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(fly4
			view: 202
			setLoop: 6
			cel: (Random 0 1)
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: picWindow
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(fly5
			view: 202
			setLoop: 6
			cel: (Random 0 1)
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: picWindow
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(fly6
			view: 202
			setLoop: 6
			cel: (Random 0 1)
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: picWindow
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(picWindow left: 60 right: 260 bottom: 150 top: 40 init:)
		(Display 780 0
			p_at 100 30
			p_width 240
			p_color vWHITE
			p_back -1
			p_font SYSFONT
		)
		(cSound number: 5 loop: -1 play:)
	)
	
	(method (doit)
		(ripple posn: (+ (skiff x?) 37) (+ (skiff y?) 1))
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(if
					(or
						(== (event message?) `S)
						(== (event message?) `s)
					)
					(cls)
					(curRoom newRoom: 781)
				)
			)
		)
	)
	
	(method (cue)
		(cls)
		(curRoom newRoom: 781)
	)
)

(instance Polling of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 50)))
			(1
				(if (skiff cel?)
					(skiff setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(skiff setCycle: EndLoop self)
				(= state -1)
			)
		)
	)
)

(instance Delay of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 50))
			(1
				(= saveBits2
					(Display 780 1
						p_at 61 44
						p_width 240
						p_color vBLACK
						p_back -1
						p_font 41
						p_save
					)
				)
				(= saveBits
					(Display 780 1
						p_at 60 43
						p_width 240
						p_color vWHITE
						p_back -1
						p_font 41
						p_save
					)
				)
			)
		)
	)
)

(instance reflection of Prop)

(instance skiff of Actor)

(instance ripple of Actor)

(instance fly1 of Actor)

(instance fly2 of Actor)

(instance fly3 of Actor)

(instance fly4 of Actor)

(instance fly5 of Actor)

(instance fly6 of Actor)

(instance picWindow of Cage)
