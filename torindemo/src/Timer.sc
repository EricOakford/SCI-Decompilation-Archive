;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	TIMER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Greg Tomko-Pavia 
;;;;  Modification date: Feb 1995
;;;;
;;;;	A Timer is a device that waits a specified duration and then
;;;;	Cues its client.
;;;;
;;;;	An important concept relating to Timers is that of game versus real
;;;;	time.  Real time is just what it sounds like -- real time in real
;;;;	seconds.  Game time is time in game doit cycles, which is a function of
;;;;	the speed of the user's computer.
;;;;
;;;;	For example:  Assume that you're writing a game in which ego has only
;;;;	five seconds to leave a room before a bomb blows up.  If you set this
;;;;	time interval as real time, it may give you just enough time to get out
;;;;	on your nice fast 486 machine, but on a Tandy 1000, where it takes 0.2s
;;;;	to complete a doit cycle, the user will not be able
;;;;	to go as far and thus has no chance of leaving the room before the bomb
;;;;	blows.  The time interval should really have been set in game time,
;;;;	which would have given the user the same number of cycles get out.
;;;;
;;;;	As a rule, timers meant to constrain the time a user has	to accomplish 
;;;;	something should be set in game time, whereas time intervals which
;;;;	are just meant to be a delay between two events	should be real time.
;;;;	A user with a slow machine has no desire to watch a banner screen 
;;;;	twice as long as one with a fast machine.
;;;;
;;;;	Classes:
;;;;		Timer
;;;;		TimeOut


(script# TIMER)
(include game.sh)
(use Main)
(use System)


(class Timer kindof Object
	;;; The Timer class implements the concept of an alarm clock,
	;;; cue:ing another object after a certain interval.  Timers
	;;; dispose: of themselves after cue:ing their client, and always
	;;; check to see if their client is still present before cue:ing it.

	;; NOTE: with the demise of IsObject, it is no longer possible to 
	;; check for client's existence. We can, however, make sure the client
	;; object is *appropriate*, which is done in CueClient. By the way, 
	;; CueClient has been left as a "helper procedure" merely for historical
	;; purposes. -gtp 
	
	
	(properties
		cycleCnt 	-1			;number of animation cycles left before done
		seconds 		-1			;number of seconds left before done
		ticks			-1			;ticks before done
		lastTime 	-1			;private
		client 		0 			;who to cue: when done
	)
	
;;;	(methods
;;;		set					;set number of game-time seconds
;;;		setCycle				;set number of animation cycles
;;;		setReal				;set number of real-time seconds
;;;		delete				;do actual disposal of the Time
;;;		setTicks				;set time based on 60ths of a second
;;;		cue					; left to the application to define if needed
;;;	)
	
	
	(procedure (CueClient &tmp who)
		;; A helper procedure.  Timer has expired, so detach from client
		;; and cue: it.
		
		;Detach ourselves from our client, but remember who it was.
		(= who client)
		(= client 0)
		
		(if who
			(if (who respondsTo:#timer:)
				(who timer:0)
			)
			(if (who respondsTo:#cue:)
				(who cue:)
			)
		)
	)
	
	
	(method (new)
		;; Create a new timer.  If the class itself is being asked to
		;; perform new:, return a new instance.  Otherwise, return the
		;; instance that is being asked.
		
		(return
			(if (== self Timer)
				(super new:)
			else
				self
			)
		)
	)
	
	
	(method (init caller &tmp t)
		;; Set this timer's client to caller, add it to the master game
		;; timer list, and attach to the client.
		
		;Set our client.
		(= client caller)
		
		;Add ourselves to the timer list.
		(timers add:self)
		
		;Tell the caller who we are.
		(if (caller respondsTo:#timer:)		;Pablo 12/5/88
			(= t (caller timer?))
			;If the caller has a timer already attached, dispose that timer.
			(if (and t (!= t self)) (t dispose:) )
			(caller timer:self)
		)
	);init
	
	
	(method (doit &tmp theTime)
		;; See if this timer has timed out.  If so, cue the client and
		;; dispose: the timer.
		
		(cond
			((!= cycleCnt -1)
				;This is a cycle counter.
				(if (not (-- cycleCnt)) (CueClient))
			)
			((!= seconds -1)
				;This is a real-time counter.
				(if (!= lastTime (= theTime (GetTime TRUE)))	 ;gets packed HMS
					(= lastTime theTime)
					(if (not (-- seconds)) (CueClient))
				)
			)
			(else
				;This is a 60ths second real-time counter.
				(if (> (- gameTime ticks) 0 )	;
					(CueClient)
				)
			)
		)
	)
	


	(method (dispose)
		;; Simply unhook the timer from its client -- the actual
		;; disposal will be done by the main game loop invoking the
		;; delete: method of all timers without clients.
		
		(if (and client (client respondsTo:#timer:))
			(client timer:0)
		)
		(= client 0)
	)
	
	
	(method (delete)
		;; Do the actual deletion of the Timer.
		
		(if (== client 0)
			(timers delete:self)
			(super dispose:)
		)
	)
	
	
	(method (setCycle caller cycles &tmp aTimer)
		;; Set the timer to go off after a certain number of animation
		;; cycles.
		
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, cycleCnt:cycles)
		(return aTimer)
	)


	; I have eliminated the obviously non-working method below.
	; Use setReal to set timer for seconds, setTicks for 1/60 seconds, and
	; setCycles to use game doits. -gtp
	
;¯gtp¯	
;¯gtp¯	(method (set caller sec min hr &tmp aTimer theTicks theSpeed)
;¯gtp¯		;; Set the timer to go off after a certain number of seconds of
;¯gtp¯		;; game time (real time on fast machines, slower on slow
;¯gtp¯		;; machines).
;¯gtp¯		
;¯gtp¯		(define	ticksPerSec		60)
;¯gtp¯		(define	ticksPerMin		3600)
;¯gtp¯		(define	minPerHr			60)
;¯gtp¯		
;¯gtp¯		(= theSpeed 6)
;¯gtp¯		(= theTicks (/ (* sec ticksPerSec) theSpeed))
;¯gtp¯		(if (> argc 2)
;¯gtp¯			(+= theTicks (/ (* min ticksPerMin) theSpeed))
;¯gtp¯		)
;¯gtp¯		(if (> argc 3)
;¯gtp¯			;This is a bit funny in order to avoid overflow.
;¯gtp¯			(+= theTicks (* (/ (* hr ticksPerMin) theSpeed) minPerHr))
;¯gtp¯		)
;¯gtp¯		(= aTimer (if (& -info- CLASS) (self new:) else self))
;¯gtp¯		(aTimer
;¯gtp¯			init: 		caller,
;¯gtp¯			cycleCnt:	theTicks
;¯gtp¯		)
;¯gtp¯		(return aTimer)
;¯gtp¯	)
;¯gtp¯ 
	
	(method (set) (return (self setReal: &rest)) )
	
	(method (setReal caller sec min hr &tmp aTimer secs)
		;; Set the timer to go off after a certain period of real time.
		
		(= secs (++ sec) )  ; add 1 so it waits that last second
		(if (> argc 2)
			(+= secs (* min 60))
		)
		(if (> argc 3)
			(+= secs (* hr 3600))
		)
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, seconds:secs)
		(return aTimer)
	)

	(method (setTicks caller theTicks &tmp aTimer)
		;; Set the timer to go off after a certain period of real time.
		
		(= aTimer
			(if (& -info- CLASS)
				(self new:)
			else
				self
			)
		)
		(aTimer 
			ticks: 		(+ (GetTime) theTicks tickOffset),	 
			init:			caller						 
		)
		(return aTimer)
	)
)

(class TimeOut of Object
	; static timer counts down elapsed cycles only
	; intended to be interrogated by interested parties
	; recommended that rm000 include kindofs for visibility
	
	(properties
		name {TO}
		timeLeft 0
	)
	
;;;	(methods
;;;		set
;;;	)
	
	(method (set cToCount)
		(= timeLeft cToCount)
	)
	
	(method (doit)
		(if timeLeft
			(-- timeLeft)
		)
	)
)