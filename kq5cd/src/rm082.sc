;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use LoadMany)
(use RFeature)
(use Motion)
(use System)

(public
	rm082 0
)

(instance rm082 of KQ5Room
	(properties
		picture 82
		west 83
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(LoadMany 128 792)
		(ego normal: 0 view: 792 setStep: 3 2 init:)
		(switch prevRoomNum
			(81
				(ego posn: 345 46 setLoop: 1)
				(self setScript: enterScreen)
			)
			(83
				(ego posn: -35 178 setStep: 3 2 setLoop: 2)
				(self setScript: enterScreenAgain)
			)
		)
		((ego head?) hide:)
		(if (not (Btst 46)) (Bset 46) (SolvePuzzle 2))
		(if (!= (theMusic number?) 87)
			(theMusic number: 87 loop: -1 playBed:)
		)
		(self setFeatures: room)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
)

(instance enterScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 165 55 self)
			)
			(1
				(ego setMotion: MoveTo 144 59 self)
			)
			(2
				(ego setMotion: MoveTo 121 69 self)
			)
			(3
				(ego
					setLoop: 0
					posn: 117 60
					setMotion: MoveTo 54 131 self
				)
			)
			(4
				(ego setMotion: MoveTo 30 152 self)
			)
			(5
				(ego setMotion: MoveTo 23 158 self)
			)
			(6
				(ego
					setLoop: 1
					posn: 25 168
					setMotion: MoveTo -22 168 self
				)
			)
			(7
				(theMusic fade:)
				(if (== ((inventory at: 33) owner?) 83)
					(curRoom newRoom: 681)
				else
					(curRoom newRoom: 83)
				)
			)
		)
	)
)

(instance enterScreenAgain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 19 164 self)
			)
			(1
				(ego setLoop: 3 setMotion: MoveTo 36 142 self)
			)
			(2
				(ego setMotion: MoveTo 45 133 self)
			)
			(3
				(ego posn: 48 133 setLoop: 3 setMotion: MoveTo 83 90 self)
			)
			(4
				(ego setMotion: MoveTo 117 67 self)
			)
			(5
				(ego setMotion: MoveTo 122 65 self)
			)
			(6
				(ego
					setLoop: 2
					posn: 126 59
					setMotion: MoveTo 159 51 self
				)
			)
			(7
				(ego setMotion: MoveTo 340 43 self)
			)
			(8
				(theMusic fade:)
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
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
					(proc0_29 710)
					(event claimed: 1)
				)
			)
		)
	)
)
