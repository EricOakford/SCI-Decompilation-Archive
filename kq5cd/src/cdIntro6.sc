;;; Sierra Script 1.0 - (do not remove this comment)
(script# 655)
(include sci.sh)
(use Main)
(use AudioScript)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)

(public
	cdIntro6 0
)

(instance cdIntro6 of Rm
	(properties
		picture 72
		style $000a
	)
	
	(method (init)
		(HandsOff)
		(Load rsPIC 72)
		(theGame setCursor: invCursor 1)
		(self setScript: sceneSixScript setRegions: 769)
		(super init:)
		(UnLoad 129 71)
		(Load rsSCRIPT 929)
		(LoadMany 128 98 1070)
		(Load 142 866)
		(intro_eyes init:)
		(intro_mouth init:)
	)
)

(instance sceneSixScript of AudioScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(intro_mouth play: 10106 653)
				(= waitForCue 5636)
			)
			(1
				(= global380 1)
				(= waitForCue 5892)
			)
			(2
				(intro_mouth setCel: 4)
				(= cycles 1)
			)
			(3
				(if (> (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(4 (curRoom newRoom: 653))
		)
	)
)

(instance intro_eyes of Prop
	(properties
		x 154
		y 54
		view 98
		priority 10
		signal $0810
	)
	
	(method (doit)
		(switch (Random 1 40)
			(1 (intro_eyes setCycle: End))
		)
		(super doit:)
	)
)

(instance intro_mouth of MonoAudioProp
	(properties
		x 152
		y 75
		view 1070
		loop 1
		cel 4
		priority 10
		signal $0810
		cycleSpeed 1
	)
)
