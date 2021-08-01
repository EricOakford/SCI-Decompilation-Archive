;;; Sierra Script 1.0 - (do not remove this comment)
(script# 771)
(include sci.sh)
(use Main)
(use Sync)
(use Actor)
(use System)


(class AudioScript of Script
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		register 0
		script 0
		caller 0
		next 0
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
			(= waitForCue 0)
		)
	)
)

(class MonoAudioProp of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		number 0
		audioEndRoom 0
		audioStarted 0
	)
	
	(method (doit)
		(if
		(and (== (DoAudio 6) -1) audioStarted audioEndRoom)
			(curRoom newRoom: audioEndRoom)
		)
		(super doit: &rest)
	)
	
	(method (play theNumber theAudioEndRoom)
		(if argc
			(= number theNumber)
			(if (> argc 1) (= audioEndRoom theAudioEndRoom))
		)
		(if number
			(self setCycle: MouthSync number)
			(theAudio number: number loop: 1 play:)
		else
			(self dispose:)
		)
		(= audioStarted 1)
	)
)
