;;; Sierra Script 1.0 - (do not remove this comment)
(script# 206)
(include sci.sh)
(use Main)
(use PriorityTalker)
(use Talker)
(use Window)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm206 0
	quirkTalker 14
	alienTalker 18
)

(instance rm206 of Rm
	(properties
		picture 91
		style $000a
	)
	
	(method (init)
		(curRoom setRegions: 210)
		(theGame handsOff:)
		(steam init:)
		(self setScript: sAlienMeanWhile)
		(super init:)
	)
)

(instance sAlienMeanWhile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(theMusic1 number: 24 loop: -1 play:)
				(quirk init:)
				(= ticks 20)
			)
			(2
				(quirk dispose:)
				(viewPort
					init:
					setStep: 5 5
					setMotion: MoveTo 122 36 self
				)
				(theMusic2 number: 423 loop: 1 play:)
			)
			(3
				(theMusic2 stop:)
				(= ticks 20)
			)
			(4
				(viewMessage init: setLoop: 3 setCycle: End self)
			)
			(5
				(viewPort setLoop: 0 setCel: 0 stopUpd:)
				(= seconds 1)
				(viewMessage dispose:)
			)
			(6
				(alienEyes init: cycleSpeed: 15 setCycle: Fwd self)
				(= seconds 2)
			)
			(7
				(alienEyes dispose:)
				(= cycles 2)
			)
			(8 (messager say: 1 0 0 0 self))
			(9
				(viewPort setLoop: 4 setCel: 0)
				(viewMessage init: setLoop: 5 setCel: 0)
				(= ticks 90)
			)
			(10 (quirk init:) (= ticks 30))
			(11
				(quirk dispose:)
				(theMusic2 number: 423 loop: 1 play:)
				(viewPort setMotion: MoveTo 122 106 self)
			)
			(12
				(theMusic2 stop:)
				(theMusic1 fade:)
				(curRoom newRoom: 201)
				(self dispose:)
			)
		)
	)
)

(instance viewMessage of Prop
	(properties
		x 122
		y 36
		view 211
		loop 3
		priority 6
		signal $0010
	)
	
	(method (doit)
		(self y: (viewPort y?))
		(super doit: &rest)
	)
)

(instance quirk of View
	(properties
		x 87
		y 115
		view 211
		loop 6
		signal $4000
	)
)

(instance viewPort of Actor
	(properties
		x 122
		y 106
		view 211
		loop 4
		priority 5
		signal $4010
	)
	
	(method (init)
		(self setLoop: 4 setCel: 0)
		(super init: &rest)
	)
)

(instance steam of Prop
	(properties
		x 272
		y 67
		view 211
		loop 7
		cel 2
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init: &rest)
	)
)

(instance alienTalker of PriorityTalker
	(properties
		x 118
		y 35
		view 211
		priority 6
		signal $6011
		talkWidth 85
		textX -110
	)
	
	(method (init)
		(self setPri: 6)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 80
			tailY: 65
			xOffset: -10
		)
		(super init: 0 0 alienMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow SysWindow)
		(super dispose: &rest)
	)
)

(instance alienMouth of Prop
	(properties
		nsTop 36
		nsLeft 19
		view 211
		loop 1
		signal $4011
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance alienEyes of Prop
	(properties
		x 126
		y 58
		view 211
		loop 2
		priority 6
		signal $4011
	)
	
	(method (init)
		(self setPri: 6)
		(super init: &rest)
	)
)

(instance quirkTalker of Narrator
	(properties
		x -22
		y 107
		signal $4001
		talkWidth 100
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 45
			tailY: 118
			xOffset: 12
		)
		(super init: 0 0 0 &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)
