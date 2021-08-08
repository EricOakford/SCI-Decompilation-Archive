;;; Sierra Script 1.0 - (do not remove this comment)
(script# 765)
(include game.sh)
(use System)


(local
	local0
)
(class Audio of Object
	(properties
		number 0
		loop 1
		paused 0
		doNotStop 0
		stopped 1
	)
	
	(method (play newVol)
		(= local0 0)
		(cond 
			((DoAudio Play number)
				(= stopped FALSE)
				(if (IsObject newVol)
					(= local0 newVol)
				)
				(self check:)
			)
			((and (IsObject newVol) (!= (= local0 newVol) 0))
				(local0 cue:)
			)
		)
	)
	
	(method (stop)
		(= stopped TRUE)
		(DoAudio Stop)
	)
	
	(method (pause)
		(if (not paused)
			(DoAudio Pause)
			(self paused: TRUE)
		)
	)
	
	(method (resume)
		(if paused (DoAudio Resume)
			(self paused: FALSE)
		)
	)
	
	(method (setLoop newLoop)
		(self loop: newLoop)
	)
	
	(method (setLang newLang)
		(if argc
			;NOTE: This probably isn't the correct define, but it is the correct number 
			(DoAudio DACFound newLang)
		)
	)
	
	(method (setRate newRate)
		(if argc
			(DoAudio Rate newRate)
		)
	)
	
	(method (check &tmp temp0)
		(if (and (not stopped) (== (DoAudio Loc) -1) (== loop 1))
			(= doNotStop FALSE)
			(= stopped TRUE)
			(if (!= local0 0)
				(= temp0 local0)
				(= local0 0)
				(temp0 cue:)
			)
		)
		(if
			(and
				(not stopped)
				(== (DoAudio Loc) -1)
				(or (> loop 1) (== loop -1))
			)
			(self play:)
		)
	)
	
	(method (playBed newVol)
		(self play: &rest)
	)
)
