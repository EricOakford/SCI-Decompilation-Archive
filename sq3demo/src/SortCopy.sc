;;; Sierra Script 1.0 - (do not remove this comment)
(script# SORTCOPY)
(include game.sh)
(use Main)

(public
	SortedAdd 0
)

	(procedure (SortedAdd 
			toList fromColl 
			&tmp	node dist obj distMin objMin
			objx objy egox egoy heading extraDist
		)
		
		(while (= node (toList first:))		;initialize to a blank list
			(toList delete: (NodeValue node))
		)
		
		(= egox (ego x?))
		(= egoy (ego y?))
		(= heading (ego heading?))
		
		(= extraDist
			(cond
				((< heading 90)	(GetDistance egox egoy SCRNWIDE 0)) 
				((< heading 180)	(GetDistance egox egoy SCRNWIDE SCRNHIGH)) 
				((< heading 270)	(GetDistance egox egoy 0 SCRNHIGH)) 
				((< heading 360)	(GetDistance egox egoy 0 0)) 
			)
		)
		
		(repeat
			
			(= objMin NULL)		;reset for each pass
			(= distMin $7fff)		;largest positive int available = 32k-1
			
			(for	
				((= node (FirstNode (fromColl elements?))))
				(and
					node
					(IsObject (= obj (NodeValue node)))
				)
				((= node (NextNode node)))
				
				(if (toList contains: obj)
					(continue)
				)
				
				(= objx (obj x?))
				(= objy (obj y?))
				
				(= dist 
					(+	(GetDistance egox egoy objx objy)
						(if (> 
								(Abs (- heading (GetAngle egox egoy objx objy))) 
								90)
							extraDist
						;else	0
						)
					)
				)
				
				(if (<= dist distMin)
					(= objMin obj)
					(= distMin dist)
				)
			
			);for loop
			
			(if objMin
				(toList addToEnd: objMin)
			else
				(break) ;out of repeat
			)
			
		);repeat loop
	);sortedCpy procedure

