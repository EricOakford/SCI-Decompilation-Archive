;;; Sierra Script 1.0 - (do not remove this comment)
(script# 216)
(include game.sh)
(use Main)
(use Procs)
(use Actor)
(use System)

(public
	egoFight 0
	pointBox 1
)

(instance egoFight of Script
	(method (init)
		(mouseDownHandler add: self)
		(pointBox init: setLoop: 2 stopUpd:)
		(theIconBar disable:)
		(super init: &rest)
	)
	
	(method (doit)
		(pointBox doit:)
		(if
			(or
				(and (client fightLeft?) (> (client x?) 288))
				(and (not (client fightLeft?)) (< (client x?) 95))
			)
			((client opponent?)
				setMotion: 0
				setCycle: 0
				setScript: 0
			)
			(if ((ScriptID 220 0) script?)
				(((ScriptID 220 0) script?) dispose:)
			)
			(if script (script dispose:))
			(client endFight: TRUE canFight: FALSE)
			(client gotBeat: (ScriptID 223 1))
		else
			(super doit:)
		)
	)
	
	(method (dispose)
		(directionHandler release:)
		(mouseDownHandler delete: self)
		(theIconBar enable:)
		(pointBox dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client drawWeapons:)
				(directionHandler addToFront: client)
				(= cycles 1)
			)
			(1
				(client
					canFight: TRUE
					action: ActNone
					cycleSpeed: 18
					moveSpeed: 12
					stopUpd:
					getTired: 4 (ScriptID 223 1)
				)
			)
			(2
				(if ((client opponent?) endFight?)
					(client setScript: 0)
				else
					(= cycles 5)
				)
			)
			(3
				(ego hide:)
				(self changeState: 1)
			)
		)
	)
	
	(method (handleEvent event &tmp evtX evtY)
		(if (or script (not (client canFight?)))
			(event claimed: TRUE)
			(return)
		)
		(cond 
			((== (event type?) mouseDown)
				(= evtX (event x?))
				(cond 
					((< (= evtY (event y?)) 154)
						(cond 
							((> evtX 285)
								(if (client canFight?)
									(client canFight: FALSE)
									(if script (script dispose:))
									(self setScript: (Clone (ScriptID 217 2)) self client)
									(TrySkill WEAPON 0 20)
									(if (client tryAttack: (client opponent?))
										((client opponent?) getHit:)
									)
								else
									(event claimed: TRUE)
								)
							)
							((< evtX 265)
								(if (client canFight?)
									(client canFight: FALSE)
									(if script (script dispose:))
									(self setScript: (Clone (ScriptID 217 3)) self client)
									(TrySkill WEAPON 0 20)
									(if (client tryAttack: (client opponent?))
										((client opponent?) getHit:)
									)
								else
									(event claimed: TRUE)
								)
							)
						)
					)
					((> evtY 169)
						(cond 
							((> evtX 285)
								(if (client canFight?)
									(TrySkill DODGE 0 20)
									(client canFight: FALSE)
									(if script (script dispose:))
									(if (Random 0 1)
										(self setScript: (Clone (ScriptID 217 0)) self client)
									else
										(self setScript: (Clone (ScriptID 217 1)) self client)
									)
								else
									(event claimed: TRUE)
								)
							)
							((< evtX 265)
								(if (client canFight?)
									(client canFight: FALSE)
									(TrySkill PARRY 0 20)
									(if script (script dispose:))
									(if (== ((client opponent?) action?) 2)
										(self setScript: (Clone (ScriptID 217 5)) self client)
									else
										(self setScript: (Clone (ScriptID 217 4)) self client)
									)
								else
									(event claimed: TRUE)
								)
							)
						)
					)
				)
				(event claimed: TRUE)
			)
			(
				(and
					(<= dirStop (event message?))
					(<= (event message?) dirNW)
				)
				(switch (event message?)
					(dirNE
						(if (client canFight?)
							(client canFight: FALSE)
							(if script (script dispose:))
							(self setScript: (Clone (ScriptID 217 2)) self client)
							(TrySkill WEAPON 0 20)
							(if (client tryAttack: (client opponent?))
								((client opponent?) getHit:)
							)
						else
							(event claimed: TRUE)
						)
					)
					(dirNW
						(if (client canFight?)
							(TrySkill DODGE 0 20)
							(client canFight: FALSE)
							(if script (script dispose:))
							(self setScript: (Clone (ScriptID 217 3)) self client)
						else
							(event claimed: TRUE)
						)
					)
					(dirSE
						(if (client canFight?)
							(TrySkill DODGE 0 20)
							(client canFight: FALSE)
							(if script (script dispose:))
							(if (Random 0 1)
								(self setScript: (Clone (ScriptID 217 0)) self client)
							else
								(self setScript: (Clone (ScriptID 217 1)) self client)
							)
						else
							(event claimed: TRUE)
						)
					)
					(dirSW
						(if (client canFight?)
							(client canFight: FALSE)
							(TrySkill PARRY 0 20)
							(if script (script dispose:))
							(if (== ((client opponent?) action?) 2)
								(self setScript: (Clone (ScriptID 217 5)) self client)
							else
								(self setScript: (Clone (ScriptID 217 4)) self client)
							)
						else
							(event claimed: TRUE)
						)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(class inputBox216 of View
	(properties
		name "inputBox"
		oldX 0
		oldY 0
		relX 280
		relY 155
		first 1
		leftBor 260
		rightBor 303
		topBor 130
		botBor 175
		value 1000
	)
	
	(method (init)
		(theIconBar disable:)
		(theGame setCursor: normalCursor TRUE 280 155)
		(Bset fHideCursor)
		(self setPri: 14 ignoreActors:)
		(super init: &rest)
	)
	
	(method (doit &tmp newEvent theRelX theRelY theOldX theOldY temp5 temp6 temp7)
		(= theOldX ((= newEvent (Event new:)) x?))
		(= theOldY (- (newEvent y?) 10))
		(if first
			(= oldX theOldX)
			(= oldY theOldY)
			(= first 0)
		)
		(if (or (!= theOldX oldX) (!= theOldY oldY))
			(if (!= theOldX oldX)
				(= temp5 (- theOldX oldX))
				(= oldX theOldX)
				(cond 
					((< (= theRelX (+ relX temp5)) leftBor) (= theRelX (= oldX leftBor)))
					((> theRelX rightBor) (= theRelX (= oldX rightBor)))
				)
			else
				(= theRelX relX)
			)
			(if (!= theOldY oldY)
				(= temp6 (- theOldY relY))
				(= oldY theOldY)
				(cond 
					((< (= theRelY (+ relY temp6)) topBor) (= theRelY (= oldY topBor)))
					((> theRelY botBor) (= theRelY (= oldY botBor)))
				)
			else
				(= theRelY relY)
			)
			(= relY theRelY)
			(theGame
				setCursor: normalCursor 1 (= relX theRelX) theRelY
			)
		)
		(newEvent dispose:)
		(super doit: &rest)
	)
	
	(method (dispose)
		(Bclr fHideCursor)
		(theGame setCursor: normalCursor TRUE)
		(super dispose:)
	)
	;This didn't decompile properly, and has been commented out.
	;My guess is that this is meant to show button presses (there are
	;graphics for that in view.945)	
;;;	(method (draw &tmp temp0 temp1)
;;;		(if (< value 1)
;;;			(= value 1)
;;;		)
;;;		(if
;;;			(>
;;;				(= temp1 --UNKNOWN-PROP-NAME--) ;which property is this?
;;;				value
;;;			)
;;;			(= temp1 value)
;;;		)
;;;		(if
;;;			(>
;;;				(= temp0
;;;					(/
;;;						(+
;;;							(= temp1
;;;								(/ (+ (* temp1 100) value -1) value)
;;;							)
;;;							9
;;;						)
;;;						10
;;;					)
;;;				)
;;;				9
;;;			)
;;;			(= temp0 9)
;;;		)
;;;		(self cel: temp0 setPri: 14 stopUpd:)
;;;	)
)

(instance pointBox of inputBox216
	(properties
		x 303
		y 185
		view vCombatIcons
		loop 2
		value 1000
	)
)
