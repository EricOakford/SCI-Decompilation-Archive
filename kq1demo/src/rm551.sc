;;; Sierra Script 1.0 - (do not remove this comment)
(script# DRAGON)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm551 0
)

(local
	local0
)
(instance rm551 of Room
	(properties
		picture 551
		style (| IRISOUT BLACKOUT)
		east 502
		west 51
	)
	
	(method (init)
		(LoadMany VIEW 533 265)
		(Load SOUND 45)
		(super init:)
		(ego
			init:
			hide:
			view: 0
			setCycle: StopWalk 2
			x: 267
			y: 147
		)
		(curRoom setScript: enterEgo)
		(dragon1 init:)
		(HandsOff)
		(= local0 2)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
	)
)

(instance moveAwayFromEgo of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (< (ego x?) 5) modelessDialog)
			(modelessDialog dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 6) number: 45 loop: -1 play:)
				(-- local0)
				(dragon1 setLoop: 2 setCycle: EndLoop self)
			)
			(1
				(if local0
					(if (== local0 1) (DisplayOldGraphics))
					(self changeState: 0)
				else
					(= cycles 1)
				)
			)
			(2
				(Print 551 0 #at 25 20 #width 260 #mode 1 #dispose)
				(dragon1
					loop: 1
					setCycle: Walk
					setMotion: MoveTo -25 (ego y?)
				)
				(ego setMotion: MoveTo -5 (- (ego y?) 2))
			)
			(3 (self dispose:))
		)
	)
)

(instance enterEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poof
					init:
					x: 267
					y: 147
					priority: 12
					signal: 16
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					show:
					x: 267
					y: 147
					loop: 1
					setCycle: StopWalk 2
					priority: 12
					signal: 16
				)
				(poof setCycle: BegLoop self)
			)
			(2
				(curRoom setScript: moveAwayFromEgo)
			)
		)
	)
)

(instance dragon1 of Actor
	(properties
		x 160
		y 151
		view 533
		loop 2
		priority 12
		signal fixPriOn
	)
)

(instance poof of Prop
	(properties
		z 26
		view 265
	)
)
