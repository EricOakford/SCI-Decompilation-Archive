;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room73 0
)

(local
	caveForeground
)
(procedure (ShowCaveForeground visible)
	(if visible
		((= caveForeground (View new:))
			view: 800
			loop: 1
			posn: 108 132
			init:
			setPri: 15
			stopUpd:
		)
	else
		(caveForeground dispose:)
		(= caveForeground 0)
	)
)

(instance Room73 of Room
	(properties
		picture 73
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 800)
		(Load VIEW 904)
		(Load VIEW 950)
		(super init:)
		(self setRegions: TROLL_CAVE)
		(= isIndoors TRUE)
		(= south 76)
		(ego view: 904 xStep: 4 yStep: 1)
		(if (!= prevRoomNum 77)
			(ego posn: 138 179 init: priority: (CoordPri (ego y?)))
			(NotifyScript TROLL_CAVE 1)
		else
			(ego view: 950 posn: 290 163 init:)
			(if (not (LanternIsOn))
				(ShowCaveForeground TRUE)
			)
			(= trollAttacks FALSE)
			(ego setScript: crawl)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl:) cBLUE)
				(< (ego heading?) 180)
				(!= (ego script?) crawl)
			)
			(ego setScript: crawl)
		)
		(cond 
			((and caveForeground (LanternIsOn))
				(ShowCaveForeground FALSE)
			)
			((and (not caveForeground) (not (LanternIsOn)))
				(ShowCaveForeground TRUE)
			)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((or (Said 'crawl,exit/cave') (Said 'crawl[/noword]'))	;EO: fixed said spec
							(if (not (& (ego onControl:) cBLUE))
								(Print 800 1)
							)
						)
						((Said 'look,find/exit,exit')	;EO: fixed said spec
							(Print 73 0)
						)
						((Said 'look<out')
							(Print 73 1)
						)
						((or (Said 'look/sky') (Said 'look<up[/noword]'))
							(if isNightTime
								(Print 73 2)
							else
								(Print 73 3)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 77)
			((ScriptID TROLL_CAVE) keep: FALSE)
			(= noWearCrown FALSE)
		)
		(super newRoom: n)
	)
)

(instance crawl of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(if (> (ego heading?) 180)
					(self changeState: 10)
				else
					(self changeState: 20)
				)
			)
			(10
				(ego
					view: 950
					setLoop: 3
					xStep: 2
					setCycle: Walk
					setMotion: MoveTo 255 164 self
				)
			)
			(11
				(ego cel: 5 setLoop: 1 cycleSpeed: 1 setCycle: BegLoop self)
			)
			(12
				(ego
					view: 904
					setLoop: -1
					xStep: 4
					cycleSpeed: 0
					setCycle: Walk
					illegalBits: cWHITE
				)
				(HandsOn)
				(client setScript: 0)
				(if (LanternIsOn)
					(NotifyScript TROLL_CAVE 3)
				)
			)
			(20
				(if (LanternIsOn)
					(NotifyScript TROLL_CAVE 2)
				)
				(ego
					view: 950
					setLoop: 0
					cel: 255
					xStep: 2
					setCycle: EndLoop self
				)
			)
			(21
				(ego
					illegalBits: 0
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo (ego x?) 164 self
				)
			)
			(22
				(ego setMotion: MoveTo 296 164 self)
			)
			(23
				(ego illegalBits: cWHITE)
				(= inCutscene TRUE)
				(= isHandsOff FALSE)
				(curRoom newRoom: 77)
			)
		)
	)
)
