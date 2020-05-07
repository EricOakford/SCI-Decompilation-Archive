;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include sci.sh)
(use System)


(local
	local0
)
(class Audio of Obj
	(properties
		number 0
		loop 1
		paused 0
		doNotStop 0
		stopped 1
	)
	
	(method (play param1)
		(= local0 0)
		(cond 
			((DoAudio 2 number)
				(= stopped 0)
				(if (IsObject param1) (= local0 param1))
				(self check:)
			)
			(
			(and (IsObject param1) (!= (= local0 param1) 0)) (local0 cue:))
		)
	)
	
	(method (stop)
		(= stopped 1)
		(DoAudio 3)
	)
	
	(method (pause)
		(if (not paused) (DoAudio 4) (self paused: 1))
	)
	
	(method (resume)
		(if paused (DoAudio 5) (self paused: 0))
	)
	
	(method (setLoop param1)
		(self loop: param1)
	)
	
	(method (setRate param1)
		(if argc (DoAudio 7 param1))
	)
	
	(method (check &tmp temp0)
		(if
		(and (not stopped) (== (DoAudio 6) -1) (== loop 1))
			(= doNotStop 0)
			(= stopped 1)
			(if (!= local0 0)
				(= temp0 local0)
				(= local0 0)
				(temp0 cue:)
			)
		)
		(if
			(and
				(not stopped)
				(== (DoAudio 6) -1)
				(or (> loop 1) (== loop -1))
			)
			(self play:)
		)
	)
)
