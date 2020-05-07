;;; Sierra Script 1.0 - (do not remove this comment)
(script# QSOUND)
(include sci.sh)
(use Sound)


(class QueuedSound kindof Sound

	;;Author: Pablo Ghenis, 7/12/89
	;;
	;;A QueuedSound assumes that the absolute values of the cues it receives
	;;form a sequence, ie. 128,129,130... 
	;;
	;;cue zero means "no news"
	;;ad-hoc cues are 1 to 127, must specialize "check" to handle them
	;;sequential cues go from 128 to 64k
	;;
	;;QueuedSounds eliminate the risk off having 
	;;the animation loop overrun by rapid sound cues since it catches up by 
	;;cueing its client as many times as the latest increment in signal, thus 
	;;faking a "queue of cues" (sorry, I can't resist a pun!)
	
	
	(properties 
		name "QSnd"
	)
	
	(method (check &tmp cues theSignal)
		
		;; Used by the main game loop to determine if an animation
		;; cue or sound completion has occurred.  If so, cue:s the client.
		
		(if (u< signal 128)	
			(return FALSE)
		)
		
		(while (!= (= theSignal signal) prevSignal)
			
			;;loop body
			;;---------
			
			(if (IsObject client)
				(for
					(	(= cues (- theSignal (or prevSignal 127)))
					)
					cues
					(	(-- cues)
					)

					;;loop body
					;;---------
					(client cue: self)
				)
			)
			(= prevSignal theSignal)
		)
		(= signal 0)
		(return TRUE)
	)
)
