;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include game.sh)
(use Main)
(use Timer)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm99 0
)

(local
	[spiralStar 4]
	i
	local5
	local6
	local7
	local8
	saveBits
)
(instance rm99 of Room
	(properties
		picture 99
		style HSHUTTER
	)
	
	(method (init)
		(HandsOff)
		(= inCartoon TRUE)
		(TheMenuBar hide:)
		(StatusLine disable:)
		(= saveDisabled TRUE)
		(Load VIEW 801)
		(Load VIEW 101)
		(Load SOUND 94)
		(Load SOUND 93)
		(super init:)
		(RedrawCast)
		(for ((= i 0)) (< i 4) ((++ i))
			((= [spiralStar i] (SpiralStar new:)) init:)
		)
		(theMusic number: 94 loop: -1 play:)
		(curRoom setScript: startShip)
	)
)

(instance startShip of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Timer setReal: self 10)
			)
			(1
				(= local7 1)
				(theMusic number: 93 loop: -1 play:)
				(Timer setReal: self 2)
			)
			(2
				(= local6 1)
			)
			(3
				(Timer setReal: self 1)
			)
			(4
				(= local8 4)
				(Timer setReal: self 1)
			)
			(5
				(if local8
					(-- state)
					(Timer setCycle: self 1)
				else
					(self cue:)
				)
			)
			(6
				(= saveBits
					(Display
						{The overwhelming force of the black hole\n
						draws your ship in. Helpless to do any-\n
						thing, you and your passengers strap in\n
						and hope for the best.}
						p_width 250
						p_at 35 65
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(Timer setReal: self 10)
			)
			(7
				(= local8 5)
				(Timer setReal: self 1)
			)
			(8
				(if (> local8 1)
					(-- state)
					(Timer setCycle: self 3)
				else
					(self cue:)
				)
			)
			(9
				(Display 99 0 p_restore saveBits)
				(curRoom newRoom: 98)
			)
		)
	)
)

(class SpiralStar of Prop	;EO: not in the class table
	(properties
		view 101
		loop 0
		cel 0
		priority 13
		radius 0
		;EO: these selectors are not in the selector table
		sel_341 0
		sel_342 0
		sel_343 0
		type $0000
	)
	
	(method (doit)
		(super doit:)
		(if (< radius sel_341)
			(self sel_344:)
		else
			(if (and local8 (> radius 187))
				(+= radius sel_341)
			)
			(-= radius sel_341)
			(= sel_342 (mod (+ sel_342 sel_343 360) 360))
			(self
				posn: (+ 160 (CosMult sel_342 radius)) (+ 95 (SinMult sel_342 radius))
			)
			(switch type
				(0
					(if (and (< radius 50) (< cel (self lastCel:)))
						(++ cel)
						(self sel_341: 8)
					)
				)
				(1
					(if (and (< radius 100) (< cel (self lastCel:)))
						(++ cel)
					)
				)
				(2
					(if (and (< radius 80) (< cel (self lastCel:)))
						(++ cel)
					)
				)
			)
			(self stopUpd:)
		)
	)
	
	(method (sel_344)
		(switch type
			(0)
			(1 (= local5 0))
			(2
				(= local7 0)
				(startShip cue:)
			)
		)
		(if local6
			(= local6 0)
			(self
				type: 2
				view: 801
				setPri: 15
				setLoop: 0
				setCel: 0
				radius: 120
				sel_341: 7
				sel_342: 125
				sel_343: -5
			)
		else
			(if (and (not local5) (not local7) (== 1 (Random 1 2)))
				(= local5 1)
				(self
					type: 1
					view: 101
					setPri: 14
					setLoop: 1
					setCel: 0
					radius: (+ 187 local8)
					sel_341: 16
					sel_342: (* (- (Random 1 72) 1) 5)
					sel_343: -5
				)
			else
				(self
					type: 0
					view: 101
					setPri: 13
					setLoop: 0
					setCel: 0
					radius: (+ 187 local8)
					sel_341: (Random 5 8)
					sel_342: (* (- (Random 1 72) 1) 5)
					sel_343: (* (Random 1 2) 5)
				)
			)
			(if local8
				(-- local8)
			)
		)
		(self doit:)
	)
)
