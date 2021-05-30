;;; Sierra Script 1.0 - (do not remove this comment)
(script# 699)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room699 0
)

(local
	sparkle1
	sparkle2
	sparkle3
	sparkle4
	local4
	oldDeadState
	local6
	runTitleSequence
	local8
	[local9 3]
)
(instance Room699 of Room
	(properties
		picture 96
		style IRISIN
	)
	
	(method (init)
		(Load VIEW 879)
		(super init:)
		((View new:)
			view: 879
			loop: 0
			cel: 0
			posn: 124 192
			addToPic:
		)
		((View new:)
			view: 879
			loop: 0
			cel: 1
			posn: 165 192
			addToPic:
		)
		((View new:)
			view: 879
			loop: 0
			cel: 2
			posn: 206 192
			addToPic:
		)
		(= oldDeadState dead)
		(= dead FALSE)
		((= sparkle1 (Prop new:))
			view: 879
			setLoop: 2
			setPri: 15
			setScript: spark1
		)
		((= sparkle2 (Prop new:))
			view: 879
			setLoop: 2
			setPri: 15
			setScript: spark2
		)
		((= sparkle3 (Prop new:))
			view: 879
			setLoop: 2
			setPri: 15
			setScript: spark3
		)
		((= sparkle4 (Prop new:))
			view: 879
			setLoop: 2
			setPri: 15
			setScript: spark4
		)
	)
	
	(method (doit)
		(if (== runTitleSequence 0)
			(if
				(==
					(Print 699 0
						#time 10
						#button {Yes} 1
						#button {No} 2
					)
					2
				)
				(theGame restart:)
			)
			(playMusic cue:)
			(= inCinematic TRUE)
			(= runTitleSequence TRUE)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(event claimed: TRUE)
		(= local8 0)
		(if oldDeadState
			(= oldDeadState FALSE)
			(theGame restart:)
		)
		(return
			(cond 
				(
					(and
						(== (event type?) keyDown)
						(== (event message?) `#2)
					)
					(event claimed: TRUE)
					(DoSound SoundOn (not (DoSound SoundOn)))
				)
				(
					(or
						(== (event message?) `x)
						(== (event message?) `X)
					)
					(theGame restart:)
				)
				(else
					(cast eachElementDo: #dispose)
					(curRoom newRoom: 98)
				)
			)
		)
	)
)

(instance spark1 of Script
	
	(method (init who)
		(super init: who)
		(client init:)
	)
	
	(method (changeState newState &tmp rand)
		(switch (= state newState)
			(0
				(= rand (/ (Random 10 40) 10))
				(client
					posn:
						(switch rand
							(1 96)
							(2 135)
							(3 230)
							(4 260)
							(else  150)
						)
						(switch rand
							(1 39)
							(2 158)
							(3 18)
							(4 123)
							(else  100)
						)
					show:
					cycleSpeed: (Random 0 2)
					setCycle: EndLoop self
				)
			)
			(1
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance spark2 of Script

	(method (init who)
		(super init: who)
		(client init:)
	)
	
	(method (changeState newState &tmp rand)
		(switch (= state newState)
			(0
				(= rand (/ (Random 10 40) 10))
				(client
					posn:
						(switch rand
							(1 80)
							(2 204)
							(3 141)
							(4 267)
						)
						(switch rand
							(1 83)
							(2 121)
							(3 80)
							(4 97)
						)
					show:
					cycleSpeed: (Random 0 2)
					setCycle: EndLoop self
				)
			)
			(1
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance spark3 of Script
	
	(method (init who)
		(super init: who)
		(client init:)
	)
	
	(method (changeState newState &tmp rand)
		(switch (= state newState)
			(0
				(= rand (/ (Random 10 40) 10))
				(client
					posn:
						(switch rand
							(1 197)
							(2 182)
							(3 116)
							(4 104)
						)
						(switch rand
							(1 47)
							(2 158)
							(3 49)
							(4 185)
						)
					show:
					cycleSpeed: (Random 0 2)
					setCycle: EndLoop self
				)
			)
			(1
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance spark4 of Script

	(method (init who)
		(super init: who)
		(client init:)
	)
	
	(method (changeState newState &tmp rand)
		(switch (= state newState)
			(0
				(= rand (/ (Random 10 40) 10))
				(client
					posn:
						(switch rand
							(1 141)
							(2 102)
							(3 268)
							(4 210)
						)
						(switch rand
							(1 80)
							(2 117)
							(3 96)
							(4 112)
						)
					show:
					cycleSpeed: (Random 0 2)
					setCycle: EndLoop self
				)
			)
			(1
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance bannerSound of Sound
	(properties
		number 1
	)
)

(instance playMusic of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (bannerSound play: self))
			(1 (curRoom newRoom: 98))
		)
	)
)
