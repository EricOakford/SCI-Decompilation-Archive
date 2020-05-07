;;; Sierra Script 1.0 - (do not remove this comment)
(script# DANCE)
(include game.sh)
(use Intrface)
(use Motion)
(use System)


(class Dance of Script
	(properties
		stepType 0
		repCnt 0
		pauseCnt 0
		baseIndex 0
		fp 0
	)
	
	(method (dispose)
		(= repCnt (= pauseCnt 0))
		(super dispose:)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(= state newState)
		(= baseIndex (* state 4))
		(= stepType (self at: baseIndex))
		(if (OneOf stepType 2 3 4)
			(= repCnt (self at: (+ baseIndex 2)))
			(if (> repCnt 0) (-- repCnt))
		)
		(if (OneOf stepType 1 2 3)
			(= pauseCnt (self at: (+ baseIndex 3)))
		)
		(if (OneOf stepType 1 2 3 4)
			(client setLoop: (self at: (+ baseIndex 1)))
		)
		(self setDir: 0)
		(self doState:)
	)
	
	(method (cue)
		(cond 
			(repCnt (-- repCnt) (self doState:))
			(pauseCnt (= cycles pauseCnt) (= pauseCnt 0))
			(else (super cue: &rest))
		)
	)
	
	(method (at)
		(Printf DANCE 0 name)
	)
	
	(method (doState &tmp temp0)
		(switch stepType
			(0
				(client view: (self at: (+ baseIndex 1)) loop: 0 cel: 0)
				(self cue:)
			)
			(1
				(client cel: (self at: (+ baseIndex 2)))
			)
			(2 (self endLoop:))
			(3 (self begLoop:))
			(4
				(if
				(OneOf (= temp0 (self at: (+ baseIndex 3))) 5 3)
					(self endLoop:)
				else
					(self begLoop:)
				)
				(self setDir: temp0)
			)
			(6
				(client
					posn: (self at: (+ baseIndex 1)) (self at: (+ baseIndex 2))
				)
				(self cue:)
			)
			(5
				(client
					x: (+ (client x?) (self at: (+ baseIndex 1)))
					y: (+ (client y?) (self at: (+ baseIndex 2)))
				)
				(self cue:)
			)
			(7
				(self doSpecial: (self at: (+ baseIndex 1)))
			)
			(8 (self dispose:))
		)
	)
	
	(method (doSpecial)
		(Printf DANCE 1 name)
	)
	
	(method (begLoop)
		(client cel: (client lastCel:) setCycle: BegLoop self)
	)
	
	(method (endLoop)
		(client cel: 0 setCycle: EndLoop self)
	)
	
	(method (setDir param1)
		(client setMotion: param1)
	)
)

(class DanceQueuedSound of Dance
	(properties
		name "DQSnd"
		cycleDir 0
		theMusic 0
		moveDir 0
		disposeUs 0
		prevTime 0
		thisTime 0
	)
	
	(method (init theClient theTheMusic)
		((= client theClient) script: self)
		((= theMusic theTheMusic) playBed: self)
		(self changeState: start)
	)
	
	(method (doit)
		(if disposeUs (self dispose:))
		(super doit:)
	)
	
	(method (dispose)
		(if theMusic (theMusic dispose:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(= cycleDir 0)
		(super changeState: newState)
		(= pauseCnt 0)
	)
	
	(method (cue &tmp [temp0 51])
		(if cycleDir
			(cond 
				((== cycleDir 1)
					(if (== (client cel?) (client lastCel:))
						(= cycleDir 0)
					else
						(client cel: (+ (client cel?) 1))
					)
				)
				((not (client cel?)) (= cycleDir 0))
				(else (client cel: (- (client cel?) 1)))
			)
			(if (not cycleDir)
				(self cue:)
			else
				(switch moveDir
					(dirN
						(client y: (- (client y?) 1))
					)
					(dirS
						(client y: (+ (client y?) 1))
					)
					(dirE
						(client x: (+ (client x?) 1))
					)
					(dirW
						(client x: (- (client x?) 1))
					)
				)
			)
		else
			(super cue: &rest)
		)
	)
	
	(method (doState)
		(if (== stepType 8)
			(= disposeUs TRUE)
		else
			(super doState:)
		)
	)
	
	(method (begLoop)
		(client cel: (client lastCel:))
		(= cycleDir -1)
	)
	
	(method (endLoop)
		(client cel: 0)
		(= cycleDir dirN)
	)
	
	(method (setDir theMoveDir)
		(= moveDir theMoveDir)
	)
)
