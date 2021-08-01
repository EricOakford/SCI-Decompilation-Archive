;;; Sierra Script 1.0 - (do not remove this comment)
(script# 654)
(include sci.sh)
(use Main)
(use AudioScript)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	cdIntro5 0
)

(local
	local0
	[local1 4]
)
(instance cdIntro5 of Rm
	(properties
		picture 71
	)
	
	(method (init)
		(Load rsPIC 71)
		(super init:)
		(UnLoad 129 70)
		(Load rsSCRIPT 929)
		(LoadMany 128 757 763)
		(Load 142 10105)
		(= local0 1)
		(HandsOff)
		(theGame setCursor: invCursor 1)
		(egoHead init:)
		(ego
			view: 763
			loop: 1
			posn: 102 109
			normal: 0
			cycleSpeed: 2
			init:
			setPri: (+ (egoHead priority?) 1)
		)
		((ego head?) hide:)
		(owl
			view: 757
			setPri: 10
			ignoreActors: 1
			setLoop: 1
			init:
		)
		(intro_eyes
			setPri: (+ (owl priority?) 1)
			setLoop: 2
			posn: (+ (owl x?) 2) (- (owl y?) 34)
			setScript: blinkScript
			init:
		)
		(rWing
			view: 757
			setPri: (+ (owl priority?) 2)
			setLoop: 4
			posn: (- (owl x?) 5) (- (owl y?) 28)
			signal: 16384
			setScript: rWingScript
			init:
		)
		(lWing
			view: 757
			setPri: (+ (owl priority?) 2)
			setLoop: 5
			posn: (+ (owl x?) 7) (- (owl y?) 26)
			setScript: lWingScript
			signal: 16384
			init:
		)
		(self setRegions: 769)
		(self setScript: sceneFiveScript)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(intro_eyes setPri: (+ (owl priority?) 1))
		(rWing setPri: (+ (owl priority?) 2))
		(lWing setPri: (+ (owl priority?) 2))
	)
)

(instance sceneFiveScript of AudioScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMouth init: changeMouth: 1 play: 10105 655)
				(= waitForCue 4864)
			)
			(1
				(lWing setScript: lWingScript)
				(rWing setScript: rWingScript)
				(= waitForCue 5124)
			)
			(2
				(cls)
				(= global380 1)
				(theMouth changeMouth: 0)
				(ego hide:)
				(= waitForCue 5380)
			)
			(3
				(if (< (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(4 (= seconds 2))
			(5 (curRoom newRoom: 655))
		)
	)
)

(instance lWingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(lWing cycleSpeed: 2 setLoop: 5 setCycle: End)
				(= seconds 4)
			)
			(2
				(lWing setLoop: 6 setCycle: End)
				(= seconds 3)
			)
			(3)
			(4
				(lWing setLoop: 5 setCycle: End)
				(= seconds 2)
			)
			(5
				(lWing setLoop: 6 setCycle: End)
				(= seconds 3)
			)
			(6)
			(7
				(lWing setLoop: 5 setCycle: End)
				(= seconds 2)
			)
			(8
				(lWing setLoop: 6 setCycle: End)
				(= seconds 4)
			)
		)
	)
)

(instance rWingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(rWing cycleSpeed: 2 setCycle: End)
				(= seconds 2)
			)
			(2
				(rWing setCycle: Beg)
				(= seconds 3)
			)
			(3
				(= local0 0)
				(rWing setLoop: 7 setCycle: End)
				(= seconds 2)
			)
			(4
				(= local0 1)
				(rWing setLoop: 4 setCel: 0)
				(= cycles 1)
			)
			(5)
			(6
				(rWing setCycle: End)
				(= seconds 2)
			)
			(7
				(rWing setCycle: Beg)
				(= seconds 3)
			)
			(8)
			(9
				(rWing setCycle: End)
				(= seconds 4)
			)
			(10
				(rWing setCycle: Beg)
				(= seconds 2)
			)
			(11)
			(12
				(= local0 0)
				(rWing setLoop: 7 setCycle: End self)
			)
			(13
				(= local0 1)
				(rWing setLoop: 4 cel: 0 setCycle: End)
				(= seconds 3)
			)
			(14
				(rWing setCycle: Beg)
				(= seconds 4)
			)
		)
	)
)

(instance blinkScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== local0 0)
			(if (> (intro_eyes cel?) 0)
				(intro_eyes setCycle: Beg self)
			else
				(= cycles 1)
			)
			(= state 2)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(intro_eyes setCycle: End self)
			)
			(2
				(intro_eyes setCycle: Beg self)
				(= state -1)
			)
			(3 (client setScript: 0))
		)
	)
)

(instance owl of Actor
	(properties
		x 189
		y 86
		view 757
	)
)

(instance intro_eyes of Prop
	(properties
		view 757
	)
)

(instance rWing of Prop
	(properties
		view 757
	)
)

(instance lWing of Prop
	(properties
		view 757
	)
)

(instance theMouth of MonoAudioProp
	(properties
		view 757
	)
	
	(method (changeMouth param1)
		(switch param1
			(0
				(theMouth
					view: 763
					loop: 1
					posn: 102 109
					cycleSpeed: 2
					setPri: (+ (egoHead priority?) 1)
				)
			)
			(1
				(theMouth
					view: 757
					setLoop: 3
					posn: (+ (owl x?) 3) (- (owl y?) 30)
					setPri: (+ (owl priority?) 1)
				)
			)
		)
	)
)

(instance egoHead of Prop
	(properties
		x 81
		y 118
		view 763
		cel 2
		priority 10
		signal $6010
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd:)
	)
	
	(method (setCel)
		(super setCel: &rest)
		(self stopUpd:)
		(switch (self cel?)
			(0
				(ego hide:)
				(self posn: 101 105)
			)
			(1
				(ego hide:)
				(self posn: 103 108)
			)
			(2
				(ego show:)
				(self posn: 81 118)
			)
			(3
				(ego hide:)
				(self posn: 99 112)
			)
			(4
				(ego hide:)
				(self posn: 97 109)
			)
		)
	)
)
