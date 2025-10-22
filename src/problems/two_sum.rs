use std::collections::HashMap;

/// # LeetCode 1: Two Sum
///
/// Implementiert die Lösung für das **2Sum-Problem**.
///
/// Gegeben ist ein Array von Ganzzahlen (`nums`) und ein Zielwert (`target`),
/// finde die Indizes der beiden Zahlen, deren Summe gleich dem Zielwert ist.
///
/// # Methode: Hash Map (O(n) Time Complexity)
///
/// Die Lösung verwendet eine **Hash Map**, um die Zeitkomplexität von O(n²) auf **O(n)**
/// zu reduzieren. Beim Durchlaufen des Arrays wird für jede Zahl (`num`) geprüft,
/// ob das **Komplement** (`target - num`) bereits in der Map gespeichert wurde.
///
/// * **Map-Inhalt:** Die Schlüssel sind die Werte (`num`), die Indizes sind die Positionen (`i`).
/// * **Prüfung:** `map.get(&complement)` prüft, ob der *Partner* bereits gesehen wurde.
/// * **Rückgabe:** Wenn der Partner gefunden wird, werden dessen gespeicherter Index (`j`)
///     und der aktuelle Index (`i`) zurückgegeben.
///
/// # Komplexität
///
/// * **Zeit:** O(n), da jeder Wert im Array nur einmal in die Hash Map eingefügt
///     und einmal nachgesehen wird.
/// * **Speicher:** O(n), um bis zu $n$ Elemente in der Hash Map zu speichern.
///
/// # Beispiel (basierend auf LeetCode Test Case)
///
/// ```rust
/// use leetcode::problems::two_sum::Solution;
/// 
/// let nums = vec![2, 7, 11, 15];
/// let target = 9;
/// let result = Solution::two_sum(nums, target);
/// // Die Summe von nums[0] (2) und nums[1] (7) ist 9.
/// assert_eq!(result, vec![0, 1]);
/// ```
///
pub struct Solution; // Notwendig für die LeetCode impl Block Struktur

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        // Erstellt die Hash Map mit einer anfänglichen Kapazität, um Reallokationen zu reduzieren.
        let mut map: HashMap<i32, usize> = HashMap::with_capacity(nums.len());

        for (i, &num) in nums.iter().enumerate() {
            // Berechnet den Wert, der zur aktuellen Zahl fehlt, um das Target zu erreichen.
            let complement = target - num;

            // Prüft, ob das Komplement bereits in der Map gesehen wurde.
            if let Some(&j) = map.get(&complement) {
                // Das Komplement wurde gefunden! Gibt die Indizes zurück.
                // j ist der Index des Komplements, i ist der Index der aktuellen Zahl.
                return vec![j as i32, i as i32];
            }

            // Wenn das Komplement nicht gefunden wurde, speichere die aktuelle Zahl
            // und ihren Index für zukünftige Komplemente.
            map.insert(num, i);
        }

        // LeetCode garantiert eine Lösung, aber dies ist der Fallback.
        vec![]
    }
}