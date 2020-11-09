;;; Sierra Script 1.0 - (do not remove this comment)
(script# rOpening)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1 0
)

(local
	sparkle
	credits
	i
	sparkleSpeed
	local4
	local5
	len
	local7
)
(instance rm1 of Room
	(properties
		picture rOpening
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rOpening)
		((View new:)
			view: rOpening
			loop: 0
			cel: 0
			ignoreActors:
			setPri: 14
			posn: 249 102
			addToPic:
		)
		((View new:)
			view: rOpening
			loop: 1
			cel: 0
			ignoreActors:
			setPri: 13
			posn: 169 94
			addToPic:
		)
		((= sparkle (Prop new:))
			view: rOpening
			loop: 4
			cel: 0
			setPri: 15
			posn: 232 51
			init:
			setScript: sparkleScript
		)
		((= credits (Prop new:))
			view: vCredits
			loop: 0
			cel: 0
			ignoreActors:
			setPri: 13
			posn: 74 146
			init:
		)
		(= local4 1)
		(= len (StrLen @theLen))
		(super init:)
		(credits setScript: creditScript)
		(rm1 setScript: MusicScript)
	)
	
	(method (doit)
		(= global300 0)
		(while (< global300 25)
			(= global301 (StrAt @theLen global300))
			(StrAt @str global300 global301)
			(++ global300)
		)
		(Display @str
			p_at 10 48
			p_color 14
			p_back 4
			p_font 369
		)
		(= global300 1)
		(StrCpy @global420 @theLen)
		(while (!= global300 len)
			(= global301 (StrAt @global420 global300))
			(= local7 global300)
			(-- local7)
			(StrAt @theLen local7 global301)
			(++ global300)
		)
		(= global300 0)
		(= global301 (StrAt @global420 global300))
		(++ local7)
		(StrAt @theLen local7 global301)
		(= i 0)
		(while (< i 1220)
			(++ i)
		)
	)
)

(instance sparkleScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= sparkleSpeed (/ (Random 10 29) 10))
				(sparkle
					show:
					cycleSpeed: sparkleSpeed
					setCycle: EndLoop self
				)
			)
			(1
				(= state -1)
				(sparkle hide:)
				(= seconds (/ (Random 30 59) 10))
			)
		)
	)
)

(instance creditScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 6))
			(1 (credits setCycle: EndLoop self))
			(2
				(credits setLoop: 1)
				(cond 
					(local4 (credits setCel: 0))
					(local5 (credits setCel: 1))
					(else (credits setCel: 2))
				)
				(self cue:)
			)
			(3 (= seconds 8))
			(4
				(credits setLoop: 0 setCel: 2 setCycle: BegLoop self)
			)
			(5
				(= state 0)
				(cond 
					(local4 (= local4 0) (= local5 1) (= seconds 2))
					(local5 (= local5 0) (= seconds 2))
				)
			)
		)
	)
)

(instance MusicScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:) number: sMerryXmas loop: 1 play: self)
			)
			(1
				(curRoom newRoom: 2)
			)
		)
	)
)
