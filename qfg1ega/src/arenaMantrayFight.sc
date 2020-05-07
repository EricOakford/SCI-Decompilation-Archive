;;; Sierra Script 1.0 - (do not remove this comment)
(script# 436)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	flyRight 0
	flyLeft 1
	startup 2
)

(instance flyRight of Script
	(properties)
	
	(method (doit)
		(cond 
			((and (<= monsterHealth 0) (not (Btst FLAG_351))) (= monsterHealth 1) (client setScript: killMantray))
			((<= (client distanceTo: ego) 52) ((ScriptID 210 0) cue:))
			((and (< (client y?) 15) (Btst MANTRAY_LEFT)) (Bclr MANTRAY_LEFT))
			((and (> (client y?) 80) (not (Btst MANTRAY_LEFT))) (Bset MANTRAY_LEFT))
			(
				(and
					(!= (self state?) 1)
					(client inRect: 120 0 200 100)
				)
				(self changeState: 1)
			)
			(
				(and
					(== (self state?) 1)
					(not (client inRect: 120 0 200 100))
				)
				(self changeState: 0)
			)
			(
				(and
					(not (Btst MANTRAY_RIGHT))
					(not (client inRect: 40 0 280 100))
				)
				(Bset MANTRAY_RIGHT)
				(self changeState: 2)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 0
					cel: 0
					setStep: 4 2
					setCycle: Forward
					setMotion:
						MoveTo
						320
						(if (Btst MANTRAY_LEFT)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
				)
			)
			(1
				(client
					setLoop: 4
					cel: 0
					setMotion:
						MoveTo
						(+ (client x?) 80)
						(if (Btst MANTRAY_LEFT)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
				)
			)
			(2
				(client
					setLoop: 2
					cel: 0
					setStep: 4 1
					setMotion:
						MoveTo
						(- (client x?) 2)
						(if (Btst MANTRAY_LEFT)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 2))
			(4 (client setScript: flyLeft))
		)
	)
)

(instance flyLeft of Script
	(properties)
	
	(method (doit)
		(cond 
			((<= monsterHealth 0) (client setScript: killMantray))
			((<= (client distanceTo: ego) 52) ((ScriptID 210 0) cue:))
			((and (< (client y?) 15) (Btst MANTRAY_LEFT)) (Bclr MANTRAY_LEFT))
			((and (> (client y?) 80) (not (Btst MANTRAY_LEFT))) (Bset MANTRAY_LEFT))
			(
				(and
					(!= (self state?) 1)
					(client inRect: 120 0 200 100)
				)
				(self changeState: 1)
			)
			(
				(and
					(== (self state?) 1)
					(not (client inRect: 120 0 200 100))
				)
				(self changeState: 0)
			)
			(
			(and (Btst MANTRAY_RIGHT) (not (client inRect: 40 0 280 100))) (Bclr MANTRAY_RIGHT) (self changeState: 2))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 1
					cel: 0
					setStep: 4 2
					setCycle: Forward
					setMotion:
						MoveTo
						0
						(if (Btst MANTRAY_LEFT)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
				)
			)
			(1
				(client
					setLoop: 5
					cel: 0
					setMotion:
						MoveTo
						(- (client x?) 80)
						(if (Btst MANTRAY_LEFT)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
				)
			)
			(2
				(client
					setLoop: 3
					cel: 0
					setStep: 4 1
					setMotion:
						MoveTo
						(+ (client x?) 2)
						(if (Btst MANTRAY_LEFT)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 2))
			(4 (client setScript: flyRight))
		)
	)
)

(instance startup of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego y?) 100)
					(User canControl: FALSE)
					(ego setMotion: 0)
				)
				(client
					ignoreActors:
					ignoreHorizon:
					posn: 144 111
					setLoop: 6
					cycleSpeed: 1
					setPri: (if (< (ego y?) 100) 9 else -1)
					setCycle: Forward
					setStep: 4 2
					illegalBits: 0
					stopUpd:
				)
				(= cycles 5)
			)
			(1
				(client setCel: 1 posn: 145 110)
				(= cycles 2)
			)
			(2
				(client setCel: 0 posn: 144 111)
				(= cycles 2)
			)
			(3
				(client setCel: 1 posn: 145 110)
				(= cycles 2)
			)
			(4
				(client setCel: 0 posn: 144 111)
				(= cycles 3)
			)
			(5
				(client
					setCel: -1
					startUpd:
					setMotion: MoveTo 152 103 self
				)
			)
			(6
				(client setLoop: 0 cel: 6 setMotion: MoveTo 279 90 self)
			)
			(7
				(User canControl: TRUE)
				(client
					setLoop: 2
					cel: 0
					setPri: (if (== curRoomNum 81) 9 else -1)
					setStep: 4 1
					setMotion: MoveTo 279 79
					setCycle: EndLoop self
				)
			)
			(8 (= cycles 2))
			(9
				(Bset MANTRAY_LEFT)
				(Bset MANTRAY_RIGHT)
				(client setPri: 9 setScript: flyLeft)
			)
		)
	)
)

(instance killMantray of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 436)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client
					setLoop: 3
					setCel: -1
					cycleSpeed: 0
					setCycle: Forward
					yStep: 6
					setMotion: MoveTo (client x?) 150 self
				)
				(if
					(and
						(< (- (client x?) 20) (ego x?))
						(< (ego x?) (+ (client x?) 20))
					)
					(if (< (ego x?) 160)
						(ego setMotion: MoveTo (+ (ego x?) 40))
					else
						(ego setMotion: MoveTo (- (ego x?) 40))
					)
				)
			)
			(1
				(Bset FLAG_351)
				(= monsterHealth 0)
				(client setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
