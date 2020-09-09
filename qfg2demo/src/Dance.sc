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
	
	(method (changeState newState &tmp [str 50])
		(= state newState)
		(= baseIndex (* state 4))
		(= stepType (self at: baseIndex))
		(if (OneOf stepType DanceEndLoop DanceBegLoop DanceMove)
			(= repCnt (self at: (+ baseIndex 2)))
			(if (> repCnt 0) (-- repCnt))
		)
		(if (OneOf stepType DanceCel DanceEndLoop DanceBegLoop)
			(= pauseCnt (self at: (+ baseIndex 3)))
		)
		(if (OneOf stepType DanceCel DanceEndLoop DanceBegLoop DanceMove)
			(client setLoop: (self at: (+ baseIndex 1)))
		)
		(self setDir: dirStop)
		(self doState:)
	)
	
	(method (cue)
		(cond 
			(repCnt
				(-- repCnt)
				(self doState:)
			)
			(pauseCnt
				(= cycles pauseCnt)
				(= pauseCnt 0)
			)
			(else
				(super cue: &rest)
			)
		)
	)
	
	(method (at)
		(Printf DANCE 0 name)
	)
	
	(method (doState &tmp dir)
		(switch stepType
			(DanceView
				(client view: (self at: (+ baseIndex 1)) loop: 0 cel: 0)
				(self cue:)
			)
			(DanceCel
				(client cel: (self at: (+ baseIndex 2)))
			)
			(DanceEndLoop
				(self endLoop:)
			)
			(DanceBegLoop
				(self begLoop:)
			)
			(DanceMove
				(if (OneOf (= dir (self at: (+ baseIndex 3))) 5 3)
					(self endLoop:)
				else
					(self begLoop:)
				)
				(self setDir: dir)
			)
			(DancePosn
				(client
					posn: (self at: (+ baseIndex 1)) (self at: (+ baseIndex 2))
				)
				(self cue:)
			)
			(DanceRelPosn
				(client
					x: (+ (client x?) (self at: (+ baseIndex 1)))
					y: (+ (client y?) (self at: (+ baseIndex 2)))
				)
				(self cue:)
			)
			(DanceSpecial
				(self doSpecial: (self at: (+ baseIndex 1)))
			)
			(DanceEnd
				(self dispose:)
			)
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
	
	(method (setDir dir)
		(client setMotion: dir)
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
	
	(method (init who music)
		((= client who) script: self)
		((= theMusic music) playBed: self)
		(self changeState: start)
	)
	
	(method (doit)
		(if disposeUs
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if theMusic
			(theMusic dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(= cycleDir 0)
		(super changeState: newState)
		(= pauseCnt 0)
	)
	
	(method (cue &tmp temp0 [str 50])
		(if cycleDir
			(cond 
				((== cycleDir 1)
					(if (== (client cel?) (client lastCel:))
						(= cycleDir 0)
					else
						(client cel: (+ (client cel?) 1))
					)
				)
				((not (client cel?))
					(= cycleDir 0)
				)
				(else
					(client cel: (- (client cel?) 1))
				)
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
		(if (== stepType DanceEnd)
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
	
	(method (setDir dir)
		(= moveDir dir)
	)
)
