;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room94 0
)

(local
	theUnicorn
	closedGate
	openedGate
)
(instance block1 of Block
	(properties
		top 137
		left 58
		bottom 146
		right 152
	)
)

(instance block2 of Block
	(properties
		top 136
		left 161
		bottom 146
		right 248
	)
)

(instance Room94 of Room
	(properties
		picture 94
		style $0010
	)
	
	(method (init)
		(Load VIEW 380)
		(Load VIEW 386)
		(Load VIEW 609)
		(super init:)
		(= isIndoors TRUE)
		(ego
			posn: 149 166
			view: 2
			setStep: 2 1
			observeBlocks: block1 block2
			init:
		)
		((= closedGate (Prop new:))
			view: 609
			loop: 0
			cel: 0
			posn: 154 120
			setPri: 10
			init:
			stopUpd:
		)
		((= openedGate (Prop new:))
			view: 609
			loop: 1
			cel: 0
			posn: 245 120
			setPri: 10
			init:
			stopUpd:
		)
		(if (== unicornState uniCAPTURED)
			((= theUnicorn (Actor new:))
				view: 380
				loop: 0
				cel: 0
				posn: 203 144
				illegalBits: 0
				cycleSpeed: 2
				setPri: 9
				init:
				setScript: uniStuff
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (curRoom newRoom: 80))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((!= (event type?) saidEvent) (return TRUE))
				((Said 'open/gate')
					(cond 
						((!= (openedGate cel?) 0) (Print 94 0))
						((not (ego inRect: 150 144 163 158)) (Print 800 1))
						(else
							(ego ignoreControl: 16)
							(openedGate ignoreActors: TRUE setCycle: EndLoop gateScript)
							(if (== unicornState uniCAPTURED)
								(uniStuff changeState: 4)
							else
								(ego ignoreBlocks: block2)
							)
						)
					)
				)
				((Said 'close/gate')
					(cond 
						((== (openedGate cel?) 0) (Print 94 1))
						((not (ego inRect: 232 149 248 158)) (Print 800 1))
						(else
							(ego observeControl: 16)
							(openedGate ignoreActors: 0 setCycle: BegLoop gateScript)
						)
					)
				)
				((Said 'converse[/unicorn,horse]')
					(if
					(or (!= unicornState uniCAPTURED) (not (cast contains: theUnicorn)))
						(Print 94 2)
					else
						(Print 94 3)
					)
				)
				((Said 'kiss')
					(if (== unicornState uniCAPTURED)
						(Print 94 4)
					else
						(Print 94 5)
					)
				)
				((Said '/unicorn,horse>')
					(cond 
						((!= unicornState uniCAPTURED) (Print 94 5) (event claimed: 1))
						((Said 'look/*')
							(if (== unicornState uniCAPTURED)
								(Print 94 6)
								(Print 94 7)
							else
								(Print 94 8)
							)
						)
						((Said 'pat') (Print 94 9))
						((Said 'mount') (Print 94 10))
						((Said 'get,capture,guide') (Print 94 11))
						((Said 'free,help') (Print 94 12))
						(else (Print 94 13) (event claimed: TRUE))
					)
				)
				((Said 'look>')
					(cond 
						((Said '/gate') (Print 94 14))
						((Said '/wall') (Print 94 15))
						((Said '[<around][/room,barn]') (Print 94 16) (if (== unicornState uniCAPTURED) (Print 94 17)))
					)
				)
			)
		)
	)
)

(instance gateScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(openedGate stopUpd:)
				(= state 0)
			)
		)
	)
)

(instance uniStuff of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (theUnicorn setCycle: EndLoop self))
			(1 (theUnicorn setCycle: EndLoop self))
			(2
				(= state -1)
				(if (not (openedGate cel?)) (= seconds (Random 2 5)))
			)
			(4 (HandsOff) (= seconds 1))
			(5
				(User canControl: FALSE)
				(ego loop: 0)
				(theGame changeScore: 4)
				(theUnicorn
					view: 386
					loop: 0
					cel: 255
					cycleSpeed: 0
					setCycle: EndLoop self
				)
				(Print 94 18 #at -1 15 #width 290)
			)
			(6
				(theUnicorn
					setLoop: 2
					illegalBits: 0
					setCycle: Forward
					setMotion: MoveTo 150 230 self
				)
			)
			(7
				(ego ignoreBlocks: block2)
				(HandsOn)
				(theUnicorn dispose:)
				(= unicornState uniFINAL)
				(= unicornRoom 27)
			)
		)
	)
)
