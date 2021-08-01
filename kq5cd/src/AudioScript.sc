;;; Sierra Script 1.0 - (do not remove this comment)
(script# 771)
(include game.sh)
(use Main)
(use Sync)
(use Actor)
(use System)


(class AudioScript of Script
	(properties
		waitForCue 0
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				waitForCue
				(u>= (& (theSync prevCue?) $fff0) (& waitForCue $fff0))
			)
			(= cycles 1)
			(= waitForCue FALSE)
		)
	)
)

(class MonoAudioProp of Prop
	(properties
		number 0
		audioEndRoom 0
		audioStarted 0
	)
	
	(method (doit)
		(if (and (== (DoAudio Loc) -1) audioStarted audioEndRoom)
			(curRoom newRoom: audioEndRoom)
		)
		(super doit: &rest)
	)
	
	(method (play soundNum roomNum)
		(if argc
			(= number soundNum)
			(if (> argc 1)
				(= audioEndRoom roomNum)
			)
		)
		(if number
			(self setCycle: MouthSync number)
			(theAudio number: number loop: 1 play:)
		else
			(self dispose:)
		)
		(= audioStarted TRUE)
	)
)
