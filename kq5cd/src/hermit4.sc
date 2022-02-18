;;; Sierra Script 1.0 - (do not remove this comment)
(script# 113)
(include sci.sh)
(use Main)
(use Intrface)
(use DLetter)
(use KQ5Room)
(use Motion)
(use Actor)
(use System)

(public
	hermit4 0
)

(instance hermit4 of KQ5Room
	(properties
		picture 47
		style $000a
	)
	
	(method (init)
		(theMusic number: 822 loop: -1 vol: 127 playBed:)
		(ego init:)
		(islets init:)
		(islets2 init:)
		(wave1 init: setScript: waves)
		(self setScript: sailInScript)
		(super init:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic fade:)
	)
)

(instance waves of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(wave1
					init:
					show:
					setCycle: EndLoop
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(wave2
					init:
					setCycle: EndLoop
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(wave3
					init:
					setCycle: EndLoop
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(wave4
					init:
					setCycle: EndLoop self
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
			)
			(1 (= seconds (Random 3 8)))
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance swim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mermaid
					setCycle: Forward
					setLoop: 9
					cycleSpeed: 2
					moveSpeed: 1
					setMotion: MoveTo 350 140 self
				)
			)
			(1 (= state -1) (= seconds 2))
		)
	)
)

(instance sailInScript of Script
	(properties)
	
	(method (doit)
		(if (sailBoat mover?) ((sailBoat mover?) doit:))
		(sail posn: (sailBoat x?) (sailBoat y?))
		(ego posn: (- (sailBoat x?) 12) (+ (sailBoat y?) 0))
		(if (cast contains: cedric)
			(cedric
				posn: (+ (sailBoat x?) 20) (+ (sailBoat y?) 3)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self doit:)
				(mermaid init: posn: 70 140 setScript: swim)
				(sailBoat
					init:
					posn: 0 140
					setPri: -1
					setCycle: Forward
					cycleSpeed: 5
					setStep: 1 1
					moveSpeed: 1
					setMotion: MoveTo 320 140 self
				)
				(sail init:)
				(ego
					normal: 0
					view: 654
					setLoop: 1
					setPri: 11
					setCel: 0
					setCycle: 0
				)
				((ego head?) hide:)
				(if (not (Btst 55)) (cedric init: z: 8))
			)
			(1 (curRoom newRoom: 89))
		)
	)
)

(instance sailBoat of Actor
	(properties
		x -35
		y 182
		yStep 1
		view 557
		xStep 1
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(KQPrint 113 0)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance sail of Prop
	(properties
		x 146
		y 133
		view 649
		signal $7900
	)
)

(instance cedric of Actor
	(properties
		view 654
		loop 2
		signal $7800
	)
)

(instance islets of Prop
	(properties
		x 160
		y 33
		view 630
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 56)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance islets2 of Prop
	(properties
		x 300
		y 33
		view 630
		cel 3
		signal $7900
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 56)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance mermaid of Actor
	(properties
		view 624
		loop 9
	)
)

(instance wave1 of Prop
	(properties
		x 199
		y 120
		view 631
	)
)

(instance wave2 of Prop
	(properties
		x 250
		y 126
		view 631
	)
)

(instance wave3 of Prop
	(properties
		x 33
		y 131
		view 631
		loop 1
	)
)

(instance wave4 of Prop
	(properties
		x 272
		y 176
		view 631
		loop 2
	)
)

(instance rwave1 of Prop
	(properties
		x 43
		y 189
		view 631
		loop 3
	)
)

(instance rwave2 of Prop
	(properties
		x 89
		y 174
		view 631
	)
)

(instance rwave3 of Prop
	(properties
		x 245
		y 106
		view 631
		loop 4
	)
)

(instance rwave4 of Prop
	(properties
		x 116
		y 158
		view 631
		loop 1
	)
)
