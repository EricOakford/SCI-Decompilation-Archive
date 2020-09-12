;;; Sierra Script 1.0 - (do not remove this comment)
(script# 385)
(include game.sh)
(use Main)
(use LLRoom)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm385 0
)

(local
	cloudState
	egoStep
	theTicks
	unfinishedBusiness
)
(instance rm385 of LLRoom
	(properties
		picture 385
		style 14
	)
	
	(method (init)
		(super init:)
		(LoadMany SOUND 387)
		(= egoStep 10)
		(cloud
			init:
			setStep: egoStep egoStep
			posn: (Random 250 255) 199
			setMotion: MoveTo (cloud x?) 14 cloud
		)
		(body init: setCycle: Forward)
		(head init: setCycle: Forward)
		(theMusic number: 386 loop: -1 play:)
		(theMusic2 number: 390 loop: -1 play: curRoom)
		(theMusic3 number: 387 loop: -1 play:)
		(curRoom setScript: sDyingInFirstClass)
		(SetFFRoom 1000 curRoom)
	)
	
	(method (cue)
		(curRoom newRoom: 390)
	)
	
	(method (newRoom)
		(globalTimer dispose: delete:)
		(super newRoom: &rest)
	)
)

(instance cloud of Actor
	(properties
		x 255
		y 189
		view 385
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (cue)
		(switch (++ cloudState)
			(1
				(StartTimer (Random 3 7) 1 self)
			)
			(2
				(if (< egoStep 44) (= egoStep (+ egoStep 2)))
				(cloud
					setStep: egoStep egoStep
					posn: (Random 250 255) 199
					setMotion: MoveTo (cloud x?) 14 self
				)
				(= cloudState 0)
			)
		)
	)
)

(instance head of Prop
	(properties
		x 215
		y 78
		view 386
		loop 1
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance body of Prop
	(properties
		x 173
		y 146
		view 386
		priority 10
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance theMusic3 of Sound)

(instance sDyingInFirstClass of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 6))
			(1 (TimePrint 385 0 #dispose self))
			(2 (= ticks 30))
			(3 (TimePrint 385 1 #dispose self))
			(4 (= ticks 30))
			(5 (TimePrint 385 2 #dispose self))
			(6 (= ticks 30))
			(7 (TimePrint 385 3 #dispose self))
			(8
				(= ticks 30)
				(= register 64)
			)
			(9
				(if (Btst register)
					(= cycles 1)
					(= theTicks 2)
				else
					(switch register
						(fLookAtBronzeAward
							(Say ego 385 4 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fGotCharger
							(Say ego 385 5 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fGotTapes
							(Say ego 385 6 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fErasedTapes	;EO: since this flag was mistakenly never set,
										;the message appears even if you did degauss the tapes.
							(Say ego 385 7 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fSearchDayTrotter
							(Say ego 385 8 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fReadMagazine
							(Say ego 385 9 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fReturnedSkates
							(Say ego 385 10 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fTookDoily
							(Say ego 385 11 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fPassedDentishQuiz
							(Say ego 385 12 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fSeeChiChiBoobs
							(Say ego 385 13 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fAfterChiChi
							(Say ego 385 14 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fRecordedChiChi
							(Say ego 385 15 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fAfterMichelle
							(Say ego 385 16 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fRecordedMichelle
							(Say ego 385 17 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fAfterLana
							(Say ego 385 18 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(fRecordedLana
							(Say ego 385 19 #dispose self)
							(= unfinishedBusiness 1)
							(= theTicks 30)
						)
						(else 
							(= theTicks 2)
							(= cycles 1)
						)
					)
				)
			)
			(10
				(if (< (++ register) 168) (= state (- state 2)))
				(= ticks theTicks)
			)
			(11
				(if unfinishedBusiness
					(= cycles 1)
				else
					(Say ego 385 20 #dispose self)
				)
			)
			(12 (= ticks 60))
			(13 (TimePrint 385 21 #dispose self))
			(14 (= ticks 60))
			(15
				(TimePrint 385 22
					#at -1 185
					#width 280
					#title {The P. A. System}
					#dispose
					self
				)
			)
			(16 (= ticks 60))
			(17 (Say ego 385 23 #dispose self))
			(18 (= ticks 60))
			(19 (Say ego 385 24 #dispose self))
			(20 (= ticks 60))
			(21
				(TimePrint 385 25 #title {The Stewardess} #dispose self)
			)
			(22
				(TimePrint 385 26 #title {The Stewardess} #dispose self)
			)
			(23 (= ticks 60))
			(24 (Say ego 385 27 #dispose self))
			(25 (= ticks 60))
			(26
				(TimePrint 385 28 #title {The Stewardess} #dispose self)
			)
			(27 (curRoom newRoom: 390))
		)
	)
)
