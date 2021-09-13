;;; Sierra Script 1.0 - (do not remove this comment)
(script# TIMER)
(include game.sh)
(use Main)
(use System)

(define	ticksPerSec		60)
(define	ticksPerMin		3600)
(define	minPerHr			60)

(class Timer kindof Object
	;;; The Timer class implements the concept of an alarm clock,
	;;; cue:ing another object after a certain interval.  Timers
	;;; dispose: of themselves after cue:ing their client, and always
	;;; check to see if their client is still present before cue:ing it.
	
	;;; An important concept relating to Timers is that of game time
	;;; versus real time.  Real time is just what it sounds like --
	;;; real time in real seconds.  Game time is time adjusted to the
	;;; performance of the user's computer -- it is the same as real
	;;; time on a computer which is able to keep up with the animation
	;;; demands of the game, but slows down in proportion to the speed
	;;; of the user's computer when it is not able to keep up.
	
	;;; An example may help clarify this.  Say that you're writing a
	;;; game in which ego has only five seconds to leave a room before
	;;; a bomb blows up.  If you set this time interval as real time,
	;;; it may give you just enough time to get out on your nice fast
	;;; 286 or 386 machine.  But on a Tandy 1000, where it takes 1/5th
	;;; of a second to complete an animation cycle instead of the
	;;; standard 1/10th of a second, the user will only be able to go
	;;; half as far and thus has no chance of leaving the room before
	;;; the bomb blows.  The time interval should really have been set
	;;; in game time, which would have given the user the same number
	;;; of animation cycles to get out.
	
	;;; As a rule of thumb, time intervals which are meant to be a
	;;; constraint on how long the user has to do something should be
	;;; set in game time, whereas time intervals which are just meant
	;;; to be a delay between two events should be real time.  A user
	;;; with a slow machine has no desire to watch a banner screen
	;;; twice as long as one with a fast machine.
	
	(properties
		cycleCnt -1			;number of animation cycles left before done
		seconds -1			;number of seconds left before done
		lastTime -1			;private
		client 0				;who to cue: when done
	)
	
;;;	(methods
;;;		set					;set number of game-time seconds
;;;		setCycle				;set number of animation cycles
;;;		setReal				;set number of real-time seconds
;;;		delete				;do actual disposal of the Time
;;;	)
	
	
	(procedure (CueClient &tmp who)
		;; A helper procedure.  Timer has expired, so detach from client
		;; and cue: it.
		
		;Detach ourselves from our client, but remember who it was.
		(= who client)
		(= client 0)
		
		(if (IsObject who)
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
	
	
	(method (init caller)
		;; Set this timer's client to caller, add it to the master game
		;; timer list, and attach to the client.
		
		;Set our client.
		(= client caller)
		
		;Add ourselves to the timer list.
		(timers add:self)
		
		
		;Tell the caller who we are.
		(if (caller respondsTo:#timer:)		;Pablo 12/5/88
			;If the caller has a timer already attached, dispose that timer.
			(if (IsObject (caller timer?))
				((caller timer?) dispose:)
			)
			(caller timer:self)
		)
	);init
	
	
	(method (doit &tmp theTime)
		;; See if this timer has timed out.  If so, cue the client and
		;; dispose: the timer.
		
		(if (!= cycleCnt -1)
			;This is a cycle counter.
			(if (not (-- cycleCnt)) (CueClient))
		else
			;This is a real-time counter.
			(if (!= lastTime (= theTime (GetTime TRUE)))
				(= lastTime theTime)
				(if (not (-- seconds)) (CueClient))
			)
		)
	)
	
	
	(method (dispose)
		;; Simply unhook the timer from its client -- the actual
		;; disposal will be done by the main game loop invoking the
		;; delete: method of all timers without clients.
		
		(if (and (IsObject client) (client respondsTo:#timer:))
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
	
	
	(method (set caller sec min hr &tmp aTimer ticks theSpeed [str 50])
		;; Set the timer to go off after a certain number of seconds of
		;; game time (real time on fast machines, slower on slow
		;; machines).
		
;;;		(define	ticksPerSec		60)
;;;		(define	ticksPerMin		3600)
;;;		(define	minPerHr			60)
		
		(= theSpeed speed)
		(if (== theSpeed 0) (= theSpeed 1))
		(= ticks (/ (* sec ticksPerSec) theSpeed))
		(if (> argc 2)
			(+= ticks (/ (* min ticksPerMin) theSpeed))
		)
		(if (> argc 3)
			;This is a bit funny in order to avoid overflow.
			(+= ticks (* (/ (* hr ticksPerMin) theSpeed) minPerHr))
		)
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, cycleCnt:ticks)
		(return aTimer)
	)
	
	
	(method (setReal caller sec min hr &tmp aTimer secs)
		;; Set the timer to go off after a certain period of real time.
		
		(= secs sec)
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
